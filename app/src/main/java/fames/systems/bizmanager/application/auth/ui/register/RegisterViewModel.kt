package fames.systems.bizmanager.application.auth.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.auth.domain.AuthRepository
import fames.systems.bizmanager.domain.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: LiveData<String> = _confirmPassword

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable: LiveData<Boolean> = _registerEnable

    fun onRegisterChange(name: String, email: String, password: String, confirmPassword: String) {
        _name.value = name
        _email.value = email
        _password.value = password
        _confirmPassword.value = confirmPassword
        _registerEnable.value = isValidEmail(email) && isValidPassword(password) && password == confirmPassword && name.isNotEmpty()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun onRegisterSelected(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            val response = repository.register(name, email, password)
            _uiState.value = if (response) UiState.SUCCESS else UiState.ERROR
        }
    }

    fun login() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            val response = repository.login(_email.value!!, _password.value!!)
            _uiState.value = if (response) UiState.PLUS else UiState.ERROR
        }
    }

    fun finishRegister() {
        _uiState.value = UiState.IDLE
    }
}