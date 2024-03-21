package fames.systems.bizmanager.application.products.ui.newproducts

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.products.domain.ProductsRepository
import fames.systems.bizmanager.application.products.domain.models.ProductForInsert
import fames.systems.bizmanager.application.products.domain.models.SubProduct
import fames.systems.bizmanager.domain.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewProductViewModel @Inject constructor(
    private val repository: ProductsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _productName = MutableLiveData<String>()
    val productName: LiveData<String> = _productName

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _sellPrice = MutableLiveData<Double>()
    val sellPrice: LiveData<Double> = _sellPrice

    private val _expensedProducts = MutableLiveData<MutableMap<Int,SubProduct>>()
    val expensedProducts: LiveData<MutableMap<Int,SubProduct>> = _expensedProducts

    private val _insertEnable = MutableLiveData<Boolean>()
    val insertEnable: LiveData<Boolean> = _insertEnable

    fun onInsertChange(name: String, description: String, sellPrice: Double) {
        _productName.value = name
        _description.value = description
        _sellPrice.value = sellPrice
        _insertEnable.value =
            valuesNotEmpty()
    }

    fun onExpensedProductChange(position: Int, subProduct: SubProduct) {
        _expensedProducts.value?.set(position, subProduct)
    }

    fun onSaveProduct(imageUri: Uri, context: Context) {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            val newProduct = ProductForInsert(
                _productName.value!!,
                _description.value!!,
                _sellPrice.value!!,
                _expensedProducts.value!!.map { it.value }.toMutableList()
            )
            val response = repository.newProduct(newProduct, imageUri, context)
            _uiState.value =
                if (response) UiState.SUCCESS else UiState.ERROR
        }
    }

    private fun valuesNotEmpty(): Boolean {
        val values = mutableListOf(_productName.value, _description.value, _sellPrice.value.toString())
        return values.all { (it?:"").trim().isNotEmpty() }
    }
    fun finishInsert() {
        _uiState.value = UiState.IDLE
    }
}