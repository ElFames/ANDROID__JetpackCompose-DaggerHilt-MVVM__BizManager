package fames.systems.bizmanager.application.clients.ui.editclient

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.clients.domain.ClientsRepository
import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.domain.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditClientViewModel @Inject constructor(
    private val repository: ClientsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _client = MutableLiveData<Client>()
    val client: LiveData<Client> = _client

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _insertEnable = MutableLiveData<Boolean>()
    val insertEnable: LiveData<Boolean> = _insertEnable
    private val _insertSuccess = MutableLiveData<Boolean>()
    val insertSuccess: LiveData<Boolean> = _insertSuccess

    fun getClient(clientId: String) {
        _uiState.value = UiState.LOADING
        val client = repository.getClient(clientId)
        _client.value = client
        _name.value = client.name
        _email.value = client.email
        _phone.value = client.phone
        _address.value = client.address
        _insertEnable.value = true
        _uiState.value = UiState.SUCCESS
    }

    fun onInsertChange(name: String, email: String, phone: String, address: String) {
        _name.value = name
        _email.value = email
        _phone.value = phone
        _address.value = address
        _insertEnable.value =
            valuesNotEmpty() && isValidEmail(email) && isValidPhone(phone)
    }

    fun updateClient(name: String, email: String, phone: String, address: String) {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            val response = repository.updateClient(_client.value!!.id, name, email, phone, address)
            _uiState.value =
                if (response) {
                    _insertSuccess.value = true
                    UiState.SUCCESS
                } else UiState.ERROR
        }
    }

    private fun isValidPhone(phone: String): Boolean =
        phone.length == 9

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()

    private fun valuesNotEmpty(): Boolean {
        val values = mutableListOf(_name.value, _phone.value.toString())
        return values.all { (it?:"").trim().isNotEmpty() }
    }

    fun finishEdit() {
        _uiState.value = UiState.IDLE
    }
}