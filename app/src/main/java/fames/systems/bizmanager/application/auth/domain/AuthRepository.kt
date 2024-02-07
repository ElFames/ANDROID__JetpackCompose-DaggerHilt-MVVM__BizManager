package fames.systems.bizmanager.application.auth.domain

import fames.systems.bizmanager.application.auth.data.AuthService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authService: AuthService
) {
    suspend fun login(email: String, password: String): Boolean {
        return authService.login(email, password)
    }

    suspend fun register(name: String, email: String, password: String): Boolean {
        return authService.register(name, email, password)
    }
}