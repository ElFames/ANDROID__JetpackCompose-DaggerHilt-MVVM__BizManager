package fames.systems.bizmanager.application.products.domain.models

data class Product(
    val id: String,
    var name: String,
    var sellPrice: Double,
    val expenses: MutableList<SubProduct>
)

data class SubProduct(
    val id: String,
    var name: String,
    var costPrice: Double,
    var quantity: Int,
    var quantityCurrency: String
)