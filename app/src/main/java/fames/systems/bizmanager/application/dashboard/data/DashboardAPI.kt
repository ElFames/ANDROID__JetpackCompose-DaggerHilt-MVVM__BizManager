package fames.systems.bizmanager.application.dashboard.data

import fames.systems.bizmanager.application.products.domain.models.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface DashboardAPI {
    @GET
    suspend fun getNumOfSalesDay(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Int>
    @GET
    suspend fun getNumOfSalesWeek(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Int>
    @GET
    suspend fun getNumOfSalesMonth(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Int>


    @GET
    suspend fun getProfitDay(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Double>
    @GET
    suspend fun getProfitWeek(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Double>
    @GET
    suspend fun getProfitMonth(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Double>


    @GET
    suspend fun getExpensesDay(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Double>
    @GET
    suspend fun getExpensesWeek(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Double>
    @GET
    suspend fun getExpensesMonth(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Double>


    @GET
    suspend fun getIncomeDay(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Double>
    @GET
    suspend fun getIncomeWeek(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Double>
    @GET
    suspend fun getIncomeMonth(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Double>



    @GET
    suspend fun getMoreProfitDay(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<MutableList<Product>>
    @GET
    suspend fun getMoreProfitWeek(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<MutableList<Product>>
    @GET
    suspend fun getMoreProfitMonth(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<MutableList<Product>>

    @GET
    suspend fun getBestSellerDay(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<MutableList<Product>>
    @GET
    suspend fun getBestSellerWeek(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<MutableList<Product>>
    @GET
    suspend fun getBestSellerMonth(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<MutableList<Product>>

}