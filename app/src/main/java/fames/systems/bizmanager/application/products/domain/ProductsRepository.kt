package fames.systems.bizmanager.application.products.domain

import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.application.products.data.ProductsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepository @Inject constructor(
    private val productsService: ProductsService
) {
    private var products = mutableListOf<Product>()
    fun getProducts() = products
    fun loadProducts() {

    }
}