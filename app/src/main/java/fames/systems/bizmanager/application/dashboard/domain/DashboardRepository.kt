package fames.systems.bizmanager.application.dashboard.domain

import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import fames.systems.bizmanager.application.dashboard.data.DashboardService
import fames.systems.bizmanager.application.products.domain.models.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepository @Inject constructor(
    private val dashboardService: DashboardService
) {
    private val format: (Double) -> String = { "%.2f".format(it).replace('.', ',') }
    private var numOfSales: Int? = null
    private var profit: Double? = null
    private var expenses: Double? = null
    private var income: Double? = null
    private var productsMoreProfit: MutableList<Product>? = null
    private var productsBestSeller: MutableList<Product>? = null
    private var currentFilter = Filter.DIA

    suspend fun loadStatistics() {
        getNumOfSales(Filter.DIA)
        getProfit(Filter.DIA)
        getExpenses(Filter.DIA)
        getIncome(Filter.DIA)
        getBestSellers(Filter.DIA)
        getMoreProfit(Filter.DIA)
    }
    suspend fun getNumOfSales(filter: Filter): Int {
        return if (numOfSales == null || numOfSales == -1 || currentFilter != filter) {
            currentFilter = filter
            val currentNumOfSales = dashboardService.getNumOfSales(filter)
            numOfSales = currentNumOfSales
            currentNumOfSales
        } else numOfSales!!
    }

    suspend fun getProfit(filter: Filter): String {
        return if (profit == null || profit == -1.0 || currentFilter != filter) {
            currentFilter = filter
            val currentProfit = dashboardService.getProfit(filter)
            profit = currentProfit
            format(currentProfit)
        } else format(profit!!)
    }

    suspend fun getExpenses(filter: Filter): String {
        return if (expenses == null || expenses == -1.0 || currentFilter != filter) {
            currentFilter = filter
            val currentExpenses = dashboardService.getExpenses(filter)
            expenses = currentExpenses
            format(currentExpenses)
        } else format(expenses!!)
    }

    suspend fun getIncome(filter: Filter): String {
        return if (income == null || income == -1.0 || currentFilter != filter) {
            currentFilter = filter
            val currentIncome = dashboardService.getIncome(filter)
            income = currentIncome
            format(currentIncome)
        } else format(income!!)
    }

    suspend fun getMoreProfit(filter: Filter): MutableList<Product> {
        return if (productsMoreProfit.isNullOrEmpty() || currentFilter != filter) {
            currentFilter = filter
            val currentProductsMoreProfit = dashboardService.getMoreProfit(filter)
            productsMoreProfit = currentProductsMoreProfit
            currentProductsMoreProfit
        } else productsMoreProfit!!
    }

    suspend fun getBestSellers(filter: Filter): MutableList<Product> {
        return if (productsBestSeller.isNullOrEmpty() || currentFilter != filter) {
            currentFilter = filter
            val currentProductsBestSellers = dashboardService.getBestSeller(filter)
            productsBestSeller = currentProductsBestSellers
            currentProductsBestSellers
        } else productsBestSeller!!
    }


}