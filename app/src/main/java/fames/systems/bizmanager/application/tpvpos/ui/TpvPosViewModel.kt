package fames.systems.bizmanager.application.tpvpos.ui

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
import fames.systems.bizmanager.domain.models.DateTime
import fames.systems.bizmanager.domain.models.getCurrentDateTime
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TpvPosViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val clientsRepository: ClientsRepository,
    private val tpvPosRepository: TpvPosRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            productsRepository.loadProducts()
        }
    }

    private val _productsSelected = MutableLiveData<MutableList<Pair<Int, Product>>>()
    val productsSelected: LiveData<MutableList<Pair<Int, Product>>> = _productsSelected

    private val _dateTime = MutableLiveData(getCurrentDateTime())
    val dateTime: LiveData<DateTime> = _dateTime

    private val _totalPrice = MutableLiveData(0.00)
    val totalPrice: LiveData<Double> = _totalPrice

    private val _allProducts = MutableLiveData(productsRepository.getProducts())
    val allProducts: LiveData<MutableList<Product>> = _allProducts

    private val _clientSelected = MutableLiveData<Client>()
    val clientSelected: LiveData<Client> = _clientSelected

    private val _allClients = MutableLiveData(clientsRepository.getClients())
    val allClients: LiveData<MutableList<Client>> = _allClients

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSellError = MutableLiveData<Pair<Boolean, String>>()
    val isSellError: LiveData<Pair<Boolean, String>> = _isSellError

    private val _isSellSuccessful = MutableLiveData<Boolean>()
    val isSellSuccessful: LiveData<Boolean> = _isSellSuccessful

    fun selectClientById(id: String) {
        _clientSelected.value = clientsRepository.getClient(id)
    }

    fun onFinishPurchase() {
        if (_productsSelected.value.isNullOrEmpty())
            _isSellError.value = Pair(true, "Escoge almenos un producto")
        else {
            viewModelScope.launch {
                _isLoading.value = true
                val response = tpvPosRepository.onFinishPurchase(
                    _productsSelected.value!!,
                    _dateTime.value!!,
                    _totalPrice.value!!,
                    _clientSelected.value?.id.toString()
                )
                _isSellError.value = Pair(!response, "No hay conexión con el servidor")
                _isSellSuccessful.value = response
                _isLoading.value = false
            }
        }
    }

    fun selectProduct(product: Product) {
        if (_productsSelected.value == null) {
            _productsSelected.value = mutableListOf()
            _productsSelected.value!!.add(Pair(1, product))
        } else {
            _productsSelected.value?.forEachIndexed { i, lineOfProduct ->
                if (lineOfProduct.second == product) {
                    val unds = lineOfProduct.first
                    _productsSelected.value?.removeAt(i)
                    _productsSelected.value?.add(i, Pair(unds + 1, product))
                    return@forEachIndexed
                }
            }
            updateTotalPrice()
        }
    }

    fun unselectProduct(product: Product) {
        _productsSelected.value?.forEachIndexed { i, lineOfProduct ->
            if (lineOfProduct.second == product) {
                _productsSelected.value?.removeAt(i)
                return@forEachIndexed
            }
        }
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        val totalPrice = _productsSelected.value?.sumOf { it.second.sellPrice }
        _totalPrice.value = totalPrice
    }

    fun applyDiscount(discount: Int) {
        val totalPrice = _totalPrice.value!!
        val newPrice = totalPrice - ((totalPrice / 100) * discount)
        _totalPrice.value = newPrice
    }

}