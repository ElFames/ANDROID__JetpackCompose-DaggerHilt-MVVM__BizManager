package fames.systems.bizmanager.application.clients.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.clients.domain.ClientsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewClientViewModel @Inject constructor(
    private val repository: ClientsRepository
): ViewModel() {
    private val _insertEnable = MutableLiveData<Boolean>()
    val insertEnable: LiveData<Boolean> = _insertEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isInsertError = MutableLiveData<Pair<Boolean, String>>()
    val isInsertError: LiveData<Pair<Boolean, String>> = _isInsertError

    private val _insertSuccessful = MutableLiveData<Boolean>()
    val insertSuccessful: LiveData<Boolean> = _insertSuccessful

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    fun onInsertChange(name: String, email: String, phone: String, address: String) {
        _name.value = name
        _email.value = email
        _phone.value = phone
        _address.value = address
        _insertEnable.value =
            valuesNotEmpty() && isValidEmail(email) && isValidPhone(phone)
    }

    fun onSaveClient(name: String, email: String, phone: String, address: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.newClient(name, email, phone, address)
            _insertSuccessful.value = response.first!!
            _isInsertError.value = Pair(!(response.first),response.second)
            _isLoading.value = false
        }
    }

    private fun isValidPhone(phone: String): Boolean =
        phone.length == 9

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun valuesNotEmpty(): Boolean {
        val values = mutableListOf(_name.value, _email.value, _phone.value.toString(), _address.value)
        return values.all { (it?:"").trim().isNotEmpty() }
    }

    fun hideError() {
        _isInsertError.value = Pair(false, "")
    }

    fun finishInsert() {
        _insertSuccessful.value = false
    }


}