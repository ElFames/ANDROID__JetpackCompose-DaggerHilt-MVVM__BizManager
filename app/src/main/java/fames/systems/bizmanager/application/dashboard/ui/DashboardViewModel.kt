package fames.systems.bizmanager.application.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.dashboard.domain.DashboardRepository
import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.utils.Formats
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
        this._filter.value = currentFilter

        viewModelScope.launch {
            var response = true
            _uiState.value = UiState.LOADING
            response = getNumOfSales()
            response = getExpenses()
            response = getProfit()
            response = getIncome()
            response = getBestSellers()
            response = getMoreProfit()
            response = true // simulate a successful response
            _uiState.value =
                if (response) UiState.SUCCESS
                else UiState.ERROR
        }
    }

    private suspend fun getMoreProfit(): Boolean {
            val newMoreProfit = repository.getMoreProfit(_filter.value!!)
            _moreProfit.value = newMoreProfit
        return newMoreProfit.isNotEmpty()
    }

    private suspend fun getBestSellers(): Boolean {
            val newBestSellers = repository.getBestSellers(_filter.value!!)
            _bestSellers.value = newBestSellers
        return newBestSellers.isNotEmpty()
    }

    private suspend fun getNumOfSales(): Boolean {
            val newNumOfSales = repository.getNumOfSales(_filter.value!!)
            _numOfSales.value = newNumOfSales.toString()
        return newNumOfSales != -1
    }
    private suspend fun getIncome(): Boolean {
            val newIncome = repository.getIncome(_filter.value!!)
            _income.value = "$newIncome $"
        return newIncome != Formats.price(-1.0)
    }
    private suspend fun getExpenses(): Boolean {
            val newExpenses = repository.getExpenses(_filter.value!!)
            _expenses.value = "$newExpenses $"
        return newExpenses != Formats.price(-1.0)
    }
    private suspend fun getProfit(): Boolean {
            val newProfit = repository.getProfit(_filter.value!!)
            _profit.value = "$newProfit $"
        return newProfit != Formats.price(-1.0)
    }

}