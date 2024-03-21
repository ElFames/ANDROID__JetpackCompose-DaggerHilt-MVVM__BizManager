package fames.systems.bizmanager.application.products.domain

import android.content.Context
import android.net.Uri
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.application.products.data.ProductsService
import fames.systems.bizmanager.application.products.domain.models.ProductForInsert
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
    suspend fun loadProducts(): MutableList<Product> {
        return products.ifEmpty {
            products = productsService.getProducts()
            products
        }
    }

    fun getProduct(productId: String): Product {
        val product = products.find { it.id == productId }
        return product!!
    }

    suspend fun updateProduct(productId: String, productUpdated: ProductForInsert): Boolean {
        val response = productsService.updateProduct(productUpdated, productId)
        if (response) {
            val product = products.find { it.id == productId }
            product!!.name = productUpdated.productName
            product.description = productUpdated.description
            product.sellPrice = productUpdated.sellPrice
            product.expenses = productUpdated.expensedProducts
        }
        return response
    }

    suspend fun newProduct(newProduct: ProductForInsert, imageUri: Uri, context: Context): Boolean {
        val response = productsService.newProduct(newProduct)
        if (response == -1) {
            return false
        } else {
            productsService.newProductImage(response.toString(), imageUri, context)
            products.add(
                Product(
                    id = response.toString(),
                    name = newProduct.productName,
                    description = newProduct.description,
                    sellPrice = newProduct.sellPrice,
                    unds = 0,
                    expenses = newProduct.expensedProducts,
                    imageUrl = "bizmanager.herokuapp.com/productImage/$response.png"
                )
            )
            return true
        }
    }

}

val productsTest = MutableList(10) { productId ->
    Product(
        id = "1",
        name = "Producto${productId}os",
        description = "Descripción del producto $productId Descripción del producto $productId Descripción del producto $productId Descripción del producto $productId Descripción del producto $productId Descripción del producto $productId Descripción del producto $productId Descripción del producto $productId",
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
        },
        imageUrl = if (productId % 2 == 0) "https://onlineblink.com/cdn/shop/products/lifting_de_cejas_web.jpg?v=1564659442" else null
    )
}