package fames.systems.bizmanager.application.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.dashboard.domain.DashboardRepository
import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import fames.systems.bizmanager.application.products.domain.ProductsRepository
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.application.products.domain.models.SubProduct
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.utils.values.Formats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _bestSellers = MutableLiveData<MutableList<Product>>()
    val bestSellers: LiveData<MutableList<Product>> = _bestSellers

    private val _moreProfit = MutableLiveData<MutableList<Product>>()
    val moreProfit: LiveData<MutableList<Product>> = _moreProfit

    private val _income = MutableLiveData<String>()
    val income: LiveData<String> = _income

    private val _expenses = MutableLiveData<String>()
    val expenses: LiveData<String> = _expenses

    private val _profit = MutableLiveData<String>()
    val profit: LiveData<String> = _profit

    private val _numOfSales = MutableLiveData<String>()
    val numOfSales: LiveData<String> = _numOfSales

    private val _filter = MutableLiveData(Filter.DIA)
    val filter: LiveData<Filter> = _filter

    fun updateFilterStatistics(currentFilter: Filter) {
        viewModelScope.launch {
            _filter.value = currentFilter
            _uiState.value = UiState.LOADING
            var response = getStatistics(currentFilter)
            response = getBestSellers(currentFilter)
            response = getMoreProfit(currentFilter)
            _uiState.value =
                if (response) UiState.SUCCESS
                else UiState.ERROR
        }
    }

    private suspend fun getMoreProfit(currentFilter: Filter): Boolean {
        val newMoreProfit = repository.getMoreProfit(currentFilter)
        _moreProfit.value = newMoreProfit
        return newMoreProfit.isNotEmpty()
    }

    private suspend fun getBestSellers(currentFilter: Filter): Boolean {
        val newBestSellers = repository.getBestSellers(currentFilter)
        _bestSellers.value = newBestSellers
        return newBestSellers.isNotEmpty()
    }

    private suspend fun getStatistics(currentFilter: Filter): Boolean {
        val statistics = repository.getStatistics(currentFilter)
        _numOfSales.value =  statistics.numOfSales.toString() + " Uds"
        _income.value = Formats.price(statistics.income) + "€"
        _expenses.value = Formats.price(statistics.expenses) + "€"
        _profit.value = Formats.price(statistics.profit) + "€"
        return statistics.numOfSales != -1
    }

}