package fames.systems.bizmanager.application.products.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    var name: String,
    var description: String,
    var unds: Int = 0,
    var sellPrice: Double,
    var expenses: MutableList<SubProduct>,
    var imageUrl: String? = null
)

@Serializable
data class SubProduct(
    val id: String,
    var name: String,
    var costPrice: Double,
    var quantity: Int,
    var quantityCurrency: String
)