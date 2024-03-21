package fames.systems.bizmanager.application.products.domain.models

import android.net.Uri
import kotlinx.serialization.Serializable

@Serializable
data class ProductForInsert(
    var productName: String,
    var description: String,
    var sellPrice: Double,
    val expensedProducts: MutableList<SubProduct>
)