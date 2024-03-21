package fames.systems.bizmanager.application.dashboard.domain

import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import fames.systems.bizmanager.application.dashboard.data.DashboardService
import fames.systems.bizmanager.application.dashboard.domain.models.MyStatistics
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.domain.models.getMonthRangeDate
import fames.systems.bizmanager.domain.models.getTodayRangeDate
import fames.systems.bizmanager.domain.models.getWeekRangeDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepository @Inject constructor(
    private val dashboardService: DashboardService
) {
    private var numOfSales: Int? = null
    private var profit: Double? = null
    private var expenses: Double? = null
    private var income: Double? = null
    private var statistics = MyStatistics(numOfSales?:-1, profit?:-1.0, expenses?:-1.0, income?:-1.0)
    private var productsMoreProfit: MutableList<Product>? = null
    private var productsBestSeller: MutableList<Product>? = null
    private var currentFilter = Filter.DIA

    suspend fun loadStatistics() {
        getStatistics(Filter.DIA)
        getBestSellers(Filter.DIA)
        getMoreProfit(Filter.DIA)
    }
    suspend fun getStatistics(filter: Filter): MyStatistics {
        return if (statistics.numOfSales == -1 || currentFilter != filter) {
            val rangeDate = when(filter) {
                Filter.DIA -> getTodayRangeDate()
                Filter.SEMANA -> getWeekRangeDate()
                Filter.MES -> getMonthRangeDate()
            }
            currentFilter = filter
            val currentStatistics = dashboardService.getStatistics(filter, rangeDate)
            numOfSales = currentStatistics.numOfSales
            profit = currentStatistics.profit
            expenses = currentStatistics.expenses
            income = currentStatistics.income
            statistics = currentStatistics
            currentStatistics
        } else statistics
    }

    suspend fun getMoreProfit(filter: Filter): MutableList<Product> {
        return if (productsMoreProfit.isNullOrEmpty() || currentFilter != filter) {
            val rangeDate = when(filter) {
                Filter.DIA -> getTodayRangeDate()
                Filter.SEMANA -> getWeekRangeDate()
                Filter.MES -> getMonthRangeDate()
            }
            currentFilter = filter
            val currentProductsMoreProfit = dashboardService.getMoreProfit(rangeDate)
            productsMoreProfit = currentProductsMoreProfit
            currentProductsMoreProfit
        } else productsMoreProfit!!
    }

    suspend fun getBestSellers(filter: Filter): MutableList<Product> {
        return if (productsBestSeller.isNullOrEmpty() || currentFilter != filter) {
            val rangeDate = when(filter) {
                Filter.DIA -> getTodayRangeDate()
                Filter.SEMANA -> getWeekRangeDate()
                Filter.MES -> getMonthRangeDate()
            }
            currentFilter = filter
            val currentProductsBestSellers = dashboardService.getBestSeller(rangeDate)
            productsBestSeller = currentProductsBestSellers
            currentProductsBestSellers
        } else productsBestSeller!!
    }


}