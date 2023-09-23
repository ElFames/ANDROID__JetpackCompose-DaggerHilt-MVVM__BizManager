package fames.systems.bizmanager.application.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fames.systems.bizmanager.application.dashboard.domain.DashboardRepository
import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
): ViewModel() {
    fun updateFilterStatistics(filter: Filter) {

    }

    private val _bestSellers = MutableLiveData<List<String>>()
    val bestSellers: LiveData<List<String>> = _bestSellers

    private val _moreProfit = MutableLiveData<List<String>>()
    val moreProfit: LiveData<List<String>> = _moreProfit

    private val _income = MutableLiveData<List<String>>(listOf("1500$"))
    val income: LiveData<List<String>> = _income

    private val _expenses = MutableLiveData<List<String>>(listOf("700$"))
    val expenses: LiveData<List<String>> = _expenses

    private val _profit = MutableLiveData<List<String>>(listOf("800$"))
    val profit: LiveData<List<String>> = _profit

    private val _numSales = MutableLiveData<List<String>>(listOf("135"))
    val numSales: LiveData<List<String>> = _numSales


}