package fames.systems.bizmanager.application.clients.ui.myclients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.clients.domain.ClientsRepository
import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.utils.Formats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyClientsViewModel @Inject constructor(
    private val repository: ClientsRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

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
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            val clientRanking = mutableListOf<Pair<String, String>>()
            val ranking = repository.getClientRanking()
            ranking.forEach {
                clientRanking.add(Pair(it.first, Formats.price(it.second)))
            }
            _clientRanking.value = clientRanking
            _uiState.value = UiState.SUCCESS
        }
    }

    fun loadClients() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            _clients.value = repository.loadClients()
            _uiState.value = UiState.SUCCESS
        }
    }

}