package fames.systems.bizmanager.application.clients.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.clients.domain.ClientsRepository
import fames.systems.bizmanager.application.clients.domain.models.Client
import javax.inject.Inject

@HiltViewModel
class ClientDetailViewModel @Inject constructor(
    private val repository: ClientsRepository
): ViewModel() {

    private val _client = MutableLiveData<Client>()
    val client: LiveData<Client> = _client

    fun getClient(clientId: String) {
        val client = repository.getClient(clientId)
        _client.value = client
    }
}