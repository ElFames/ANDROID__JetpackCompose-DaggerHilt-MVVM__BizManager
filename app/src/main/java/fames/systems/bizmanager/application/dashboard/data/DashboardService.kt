package fames.systems.bizmanager.application.dashboard.data

import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import fames.systems.bizmanager.application.dashboard.domain.models.MyStatistics
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.domain.usecase.Session
import fames.systems.bizmanager.domain.models.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardService @Inject constructor(
    private val dashboardAPI: DashboardAPI,
    private val session: Session
) {
    private val myToken get() = session.getCurrentToken()
    private val authHeader = "Bearer $myToken"
    private val nullStatistics = MyStatistics(-1, -1.0, -1.0, -1.0)

    suspend fun getStatistics(filter: Filter, dateRange: Pair<DateTime, DateTime>): MyStatistics {
        return when (filter) {
            Filter.DIA -> dashboardAPI.getStatistics(authHeader, dateRange).body() ?: nullStatistics
            Filter.SEMANA -> dashboardAPI.getStatistics(authHeader,dateRange).body() ?: nullStatistics
            Filter.MES -> dashboardAPI.getStatistics(authHeader,dateRange).body() ?: nullStatistics
        }
    }

    suspend fun getMoreProfit(dataRange: Pair<DateTime, DateTime>): MutableList<Product> {
        return dashboardAPI.getMoreProfitByDataRange(authHeader, dataRange).body() ?: mutableListOf()
    }

    suspend fun getBestSeller(dataRange: Pair<DateTime, DateTime>): MutableList<Product> {
        return dashboardAPI.getBestSellerByDataRange(authHeader, dataRange).body() ?: mutableListOf()
    }


}