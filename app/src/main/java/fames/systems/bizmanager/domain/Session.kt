package fames.systems.bizmanager.domain

import fames.systems.bizmanager.application.auth.domain.models.MyUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Session @Inject constructor() {
    companion object {
        private var currentToken: String? = null
        private var currentUser: MyUser? = null
    }
    fun getCurrentToken() = currentToken
    fun updateToken(token: String) {
        currentToken = token
    }
    fun getCurrentUser() = currentUser
    fun setCurrentUser(user: MyUser) {
        currentUser = user
    }

}