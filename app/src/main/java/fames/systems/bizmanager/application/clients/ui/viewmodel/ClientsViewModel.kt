package fames.systems.bizmanager.application.clients.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.clients.domain.ClientsRepository
import fames.systems.bizmanager.application.clients.domain.models.Client
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor(
    private val repository: ClientsRepository
): ViewModel() {
    init {
        viewModelScope.launch {
            repository.loadClients()
        }
    }
    private val _clientToSearch = MutableLiveData("")
    val clientToSearch: LiveData<String> = _clientToSearch

    private val _clients = MutableLiveData(repository.getClients())
    val clients: LiveData<MutableList<Client>> = _clients

    private val _clientRanking = MutableLiveData<List<Pair<String, String>>>(emptyList())
    val clientRanking: LiveData<List<Pair<String, String>>> = _clientRanking

    fun onClientToSearchChange(newClientToSearch: String) {
        _clientToSearch.value = newClientToSearch
        _clients.value = repository.searchClient(newClientToSearch)
    }

    fun getClientRanking() {
        val format: (Double) -> String = { "%.2f".format(it).replace('.', ',') }
        viewModelScope.launch {
            val clientRanking = mutableListOf<Pair<String, String>>()
            val ranking = repository.getClientRanking()
            ranking.forEach {
                clientRanking.add(Pair(it.first, format(it.second)))
            }
            _clientRanking.value = clientRanking
        }
    }
}