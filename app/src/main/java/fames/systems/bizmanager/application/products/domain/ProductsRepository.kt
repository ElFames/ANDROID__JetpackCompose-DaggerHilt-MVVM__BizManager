package fames.systems.bizmanager.application.products.domain

import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.application.products.data.ProductsService
import fames.systems.bizmanager.application.products.domain.models.SubProduct
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepository @Inject constructor(
    private val productsService: ProductsService
) {
    private var products = mutableListOf<Product>()
    fun getProducts() = products
    fun loadProducts() {
        // products = productsService.getProducts()
        products = productsTest
    }
}

val productsTest = MutableList(10) { productId ->
    Product(
        id = UUID.randomUUID().toString(),
        name = "Producto${productId}sos",
        sellPrice = 20.0 + productId * 5,
        unds = 0,
        expenses = MutableList(3) { expenseId ->
            SubProduct(
                id = UUID.randomUUID().toString(),
                name = "SubProducto $expenseId",
                costPrice = 8.0,
                quantity = expenseId + 2,
                quantityCurrency = if (productId % 2 == 0) "g" else "ml"
            )
        }
    )
}