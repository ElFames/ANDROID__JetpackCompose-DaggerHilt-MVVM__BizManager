package fames.systems.bizmanager.application.tpvpos.ui.pointofsale

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.clients.domain.ClientsRepository
import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.products.domain.ProductsRepository
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.application.tpvpos.domain.TpvPosRepository
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.domain.models.getCurrentDateTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TpvPosViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val clientsRepository: ClientsRepository,
    private val tpvPosRepository: TpvPosRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _productsSelected = MutableLiveData<MutableList<Product>>()
    val productsSelected: LiveData<MutableList<Product>> = _productsSelected

    private val _dateTime = MutableLiveData(getCurrentDateTime())

    private val _totalPrice = MutableLiveData(0.00)
    val totalPrice: LiveData<Double> = _totalPrice
    private val _totalPriceOriginal = MutableLiveData(_totalPrice.value!!)

    private val _allProducts = MutableLiveData(productsRepository.getProducts())
    val allProducts: LiveData<MutableList<Product>> = _allProducts

    private val _clientSelected = MutableLiveData<Client?>(clientsRepository.getLastClientView())
    val clientSelected: LiveData<Client?> = _clientSelected

    private val _isSellEnable = MutableLiveData(true)
    val isSellEnable: LiveData<Boolean> = _isSellEnable

    fun onFinishSelection() {
        if (_productsSelected.value.isNullOrEmpty())
            _isSellEnable.value = false // dialog "Escoge almenos un producto"
        else {
            viewModelScope.launch {
                _uiState.value = UiState.LOADING
                val response = tpvPosRepository.onFinishSelection(
                    _productsSelected.value!!,
                    _dateTime.value!!,
                    _totalPrice.value!!,
                    _clientSelected.value?.id.toString()
                )
                _uiState.value =
                    if (response) UiState.SUCCESS else UiState.ERROR
            }
        }
    }

    fun selectProduct(product: Product) {
        val selectedProducts = _productsSelected.value ?: mutableListOf()

        val existingProduct = selectedProducts.find { it.id == product.id }
        if (existingProduct != null) {
            existingProduct.unds += 1
        } else {
            product.unds = 1
            selectedProducts.add(product)
        }

        _productsSelected.value = selectedProducts
        updateTotalPrice()
    }

    fun sellEnable() {
        _isSellEnable.value = true
    }

    private fun updateTotalPrice() {
        val totalPrice = _productsSelected.value?.sumOf { it.sellPrice * it.unds } ?: 0.0
        _totalPrice.value = totalPrice
        _totalPriceOriginal.value = _totalPrice.value!!
    }

    fun clearAllSelections() {
        _productsSelected.value = mutableListOf()
        _totalPrice.value = 0.0
        _totalPriceOriginal.value = _totalPrice.value!!
    }

    fun applyDiscount(discount: Int) {
        val totalPrice = _totalPriceOriginal.value!!

        if (discount == 0) updateTotalPrice()
        else {
            val newPrice = totalPrice - ((totalPrice / 100) * discount)
            _totalPrice.value = newPrice
        }

        _totalPriceOriginal.value = totalPrice
    }

    fun finishSelection() {
        _uiState.value = UiState.IDLE
    }

    fun clearClientSelected() {
        clientsRepository.setLastClientView(null)
        _clientSelected.value = null
    }

    fun checkProductIsSelected(product: Product): Int {
        _productsSelected.value?.forEach { prod ->
            if (prod == product) {
                return prod.unds
            }
        }
        return 0
    }

    fun loadProducts() {
        viewModelScope.launch {
            productsRepository.loadProducts()
            _allProducts.value = productsRepository.getProducts()
        }
    }

}