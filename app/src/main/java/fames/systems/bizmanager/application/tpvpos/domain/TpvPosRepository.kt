package fames.systems.bizmanager.application.tpvpos.domain

import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.domain.models.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TpvPosRepository @Inject constructor(

) {
    fun onFinishPurchase(
        products: MutableList<Pair<Int, Product>>,
        dateTime: DateTime,
        totalPrice: Double,
        clientId: String?
    ): Boolean {
        return true
    }

}