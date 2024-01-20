package fames.systems.bizmanager.application.dashboard.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.dashboard.domain.DashboardRepository
import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
): ViewModel() {
    init {
        getNumOfSales(Filter.DIA)
        getExpenses(Filter.DIA)
        getProfit(Filter.DIA)
        getIncome(Filter.DIA)
    }

    private val _bestSellers = MutableLiveData<List<String>>()
    val bestSellers: LiveData<List<String>> = _bestSellers

    private val _moreProfit = MutableLiveData<List<String>>()
    val moreProfit: LiveData<List<String>> = _moreProfit

    private val _income = MutableLiveData<List<String>>()
    val income: LiveData<List<String>> = _income

    private val _expenses = MutableLiveData<List<String>>()
    val expenses: LiveData<List<String>> = _expenses

    private val _profit = MutableLiveData<List<String>>()
    val profit: LiveData<List<String>> = _profit

    private val _numOfSales = MutableLiveData<List<String>>()
    val numOfSales: LiveData<List<String>> = _numOfSales

    fun updateFilterStatistics(filter: Filter) {
        getNumOfSales(filter)
        getExpenses(filter)
        getProfit(filter)
        getIncome(filter)
    }

    private fun getNumOfSales(filter: Filter) {
        viewModelScope.launch {
            val newNumOfSales = repository.getNumOfSales(filter)
            _numOfSales.value = listOf(newNumOfSales.toString())
        }
    }
    private fun getIncome(filter: Filter) {
        viewModelScope.launch {
            val newIncome = repository.getIncome(filter)
            _income.value = listOf("$newIncome $")
        }
    }
    private fun getExpenses(filter: Filter) {
        viewModelScope.launch {
            val newExpenses = repository.getExpenses(filter)
            _expenses.value = listOf("$newExpenses $")
        }
    }
    private fun getProfit(filter: Filter) {
        viewModelScope.launch {
            val newProfit = repository.getProfit(filter)
            _profit.value = listOf("$newProfit $")
        }
    }

}