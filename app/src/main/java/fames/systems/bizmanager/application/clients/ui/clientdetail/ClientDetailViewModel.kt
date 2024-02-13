package fames.systems.bizmanager.application.clients.ui.clientdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.clients.domain.ClientsRepository
import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.domain.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ClientDetailViewModel @Inject constructor(
    private val repository: ClientsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _client = MutableLiveData<Client>()
    val client: LiveData<Client> = _client

    fun getClient(clientId: String) {
        _uiState.value = UiState.LOADING
        val client = repository.getClient(clientId)
        _client.value = client
        _uiState.value = UiState.SUCCESS
    }

    fun setLastClientViewed(client: Client) {
        repository.setLastClientView(client)
    }

}