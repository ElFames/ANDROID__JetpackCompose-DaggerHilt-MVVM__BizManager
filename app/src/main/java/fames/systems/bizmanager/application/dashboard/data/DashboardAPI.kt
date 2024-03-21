package fames.systems.bizmanager.application.dashboard.data

import fames.systems.bizmanager.application.dashboard.domain.models.MyStatistics
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.domain.models.DateTime
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

interface DashboardAPI {

    @Headers("Content-Type: application/json")
    @POST("dashboard/statistics/byDateRange/all")
    suspend fun getStatistics(
        @Header("Authorization") authHeader: String,
        @Body dateRange: Pair<DateTime, DateTime>
    ): Response<MyStatistics>

    @Headers("Content-Type: application/json")
    @POST("dashboard/statistics/byDateRange/moreProfit")
    suspend fun getMoreProfitByDataRange(
        @Header("Authorization") authHeader: String,
        @Body dateRange: Pair<DateTime, DateTime>
    ): Response<MutableList<Product>>

    @Headers("Content-Type: application/json")
    @POST("dashboard/statistics/byDateRange/bestSellerProduct")
    suspend fun getBestSellerByDataRange(
        @Header("Authorization") authHeader: String,
        @Body dateRange: Pair<DateTime, DateTime>
    ): Response<MutableList<Product>>

}