package fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.clients.domain.ClientsRepository
import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.tpvpos.domain.TpvPosRepository
import fames.systems.bizmanager.domain.models.Purchase
import fames.systems.bizmanager.domain.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PurchaseInvoicerViewModel @Inject constructor(
    private val clientsRepository: ClientsRepository,
    private val tpvPosRepository: TpvPosRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _purchase = MutableLiveData<Purchase>()
    val purchase: LiveData<Purchase> = _purchase

    private val _client = MutableLiveData<Client>()
    val client: LiveData<Client> = _client

    fun finishPurchase() {
        _uiState.value = UiState.IDLE
    }

    fun getPurchase() {
        _purchase.value = tpvPosRepository.lastPurchase
    }
    fun getClient() {
        val currentClient = clientsRepository.getLastClientView()
        _client.value = currentClient ?: Client(
            id = 1,
            name = "Anónimo",
            email = "Sin Datos",
            phone = "",
            address = "",
            purchases = mutableListOf()
        )
    }

    fun onFinishPurchaseAndSendInvoice() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            val response = tpvPosRepository.onFinishPurchaseAndSendInvoice(_client.value?.email!!)
            if (response) _uiState.value = UiState.SUCCESS
            else _uiState.value = UiState.ERROR
        }
    }
    fun onFinishPurchase() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            val response = tpvPosRepository.onFinishPurchase()
            if (response) _uiState.value = UiState.SUCCESS
            else _uiState.value = UiState.ERROR
        }
    }
}