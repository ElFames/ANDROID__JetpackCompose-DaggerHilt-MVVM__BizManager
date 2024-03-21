package fames.systems.bizmanager.application.tpvpos.data

import fames.systems.bizmanager.domain.usecase.Session
import fames.systems.bizmanager.domain.models.Purchase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TpvPosService @Inject constructor(
    private val sessionService: Session,
    private val tpvPosAPI: TpvPosAPI
) {
    private val myToken get() = sessionService.getCurrentToken()
    private val authHeader = "Bearer $myToken"

    suspend fun onFinishPurchase(lastPurchase: Purchase): Boolean {
        return tpvPosAPI.onFinishPurchase(lastPurchase, authHeader).body() ?: false
    }
    suspend fun sendInvoice(email: String): Boolean {
        return tpvPosAPI.sendInvoice(email, authHeader).body() ?: false
    }

}