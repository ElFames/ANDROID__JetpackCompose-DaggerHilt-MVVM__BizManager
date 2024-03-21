package fames.systems.bizmanager.application.products.ui.myproducts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.products.domain.ProductsRepository
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.domain.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProductsViewModel @Inject constructor(
    private val repository: ProductsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _products = MutableLiveData(repository.getProducts())
    val products: LiveData<MutableList<Product>> = _products

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            _products.value = repository.loadProducts()
            _uiState.value = UiState.SUCCESS
        }
    }

}