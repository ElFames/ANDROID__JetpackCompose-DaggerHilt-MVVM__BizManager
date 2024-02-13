package fames.systems.bizmanager.domain.models

import fames.systems.bizmanager.application.products.domain.models.Product
import kotlinx.serialization.Serializable


@Serializable
data class Purchase(
    val id: String,
    val products: MutableList<Product>,
    val dateTime: DateTime,
    val totalPrice: Double,
    val clientId: String?
)