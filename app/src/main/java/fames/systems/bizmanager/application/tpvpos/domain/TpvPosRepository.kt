package fames.systems.bizmanager.application.tpvpos.domain

import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.application.tpvpos.data.TpvPosService
import fames.systems.bizmanager.domain.models.DateTime
import fames.systems.bizmanager.domain.models.Purchase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TpvPosRepository @Inject constructor(
    private val tpvPosService: TpvPosService
) {
    var lastPurchase: Purchase? = null

    fun onFinishSelection(
        products: MutableList<Product>,
        dateTime: DateTime,
        totalPrice: Double,
        clientId: String?
    ): Boolean {
        val purchase = Purchase(
            id = "",
            products = products,
            dateTime = dateTime,
            totalPrice = totalPrice,
            clientId = clientId
        )
        lastPurchase = purchase
        return true
    }

}