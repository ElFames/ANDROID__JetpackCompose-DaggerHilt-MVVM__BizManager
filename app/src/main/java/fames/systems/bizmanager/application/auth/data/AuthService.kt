package fames.systems.bizmanager.application.auth.data

import fames.systems.bizmanager.application.auth.domain.models.MyUser
import fames.systems.bizmanager.application.auth.domain.models.UserForRegister
import fames.systems.bizmanager.domain.usecase.Session
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    private val authAPI: AuthAPI,
    private val session: Session
) {
    private val authHeader = "Bearer ${session.getCurrentToken()}"

    suspend fun login(email: String, password: String): Boolean {
        try {
            var myUser = session.getCurrentUser()
            myUser =
                if(myUser != null) {
                    if (myUser.email == email && myUser.password == password) {
                        if (!checkToken()) {
                            val newToken = generateToken(email, password)
                            session.updateToken(newToken)
                        }
                        MyUser(email, password)
                    } else {
                        val newToken = generateToken(email, password)
                        session.updateToken(newToken)
                        MyUser(email, password)
                    }
                } else {
                    if (!checkToken()) {
                        val newToken = generateToken(email, password)
                        session.updateToken(newToken)
                    }
                    MyUser(email, password)
                }
            session.setCurrentUser(myUser)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    suspend fun register(username: String, email: String, password: String): Boolean {
        val response = authAPI.registerNewUser(
            UserForRegister(
                username,
                email,
                password
            )
        )
        return response.body() ?: false
    }

    private suspend fun generateToken(username: String, password: String): String {
        val response = authAPI.getNewToken(
            MyUser(
                username,
                password
            )
        )
        if (response.isSuccessful) {
            val token = response.body()?.get("token")
            return token ?: throw Exception("Token no encontrado")
        } else {
            throw Exception("Error al iniciar sesión: ${response.code()}")
        }
    }

    private suspend fun checkToken(): Boolean {
        return try {
            if (session.getCurrentToken() == null) false
            else {
                val message = authAPI.audience("auth/audience", authHeader).message()
                message != "Unauthorized"
            }
        } catch (e: Exception) {
            false
        }
    }

    suspend fun changePassword(newPassword: String): Boolean {
        val response = authAPI.changePassword(newPassword, authHeader)
        return response.body() ?: false
    }

    suspend fun changeEmail(newEmail: String): Boolean {
        val response = authAPI.changeEmail(newEmail, authHeader)
        return response.body() ?: false
    }
}
