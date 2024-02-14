package fames.systems.bizmanager.application.tpvpos.data

import fames.systems.bizmanager.domain.models.Purchase
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface TpvPosAPI {
    @Headers("Content-Type: application/json")
    @POST("tpv/finishPurchase")
    suspend fun onFinishPurchase(
        @Body lastPurchase: Purchase,
        @Header("Authorization") authHeader: String
    ): Response<Boolean>

    @Headers("Content-Type: application/json")
    @POST("tpv/finishPurchase/{email}")
    suspend fun sendInvoice(
        @Path("email") email: String,
        @Header("Authorization") authHeader: String
    ): Response<Boolean>

}