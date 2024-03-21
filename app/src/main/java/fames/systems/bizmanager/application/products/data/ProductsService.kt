package fames.systems.bizmanager.application.products.data

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.application.products.domain.models.ProductForInsert
import fames.systems.bizmanager.domain.usecase.Session
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsService @Inject constructor(
    private val sessionService: Session,
    private val productsAPI: ProductsAPI
) {
    private val myToken get() = sessionService.getCurrentToken()
    private val authHeader = "Bearer $myToken"

    suspend fun newProduct(newProduct: ProductForInsert): Int {
        return productsAPI.newProduct(newProduct, authHeader).body() ?: -1
    }
    suspend fun newProductImage(productId: String, imageUri: Uri, context: Context): Boolean {
        val file = File(getRealPathFromURI(imageUri, context))
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        val response = productsAPI.newProductImage(productId, body, authHeader)
        return response.isSuccessful
    }

    private fun getRealPathFromURI(uri: Uri, context: Context): String {
        var realPath = uri.path?:""
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.let {
            it.moveToFirst()
            val idx = it.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            realPath = it.getString(idx)
            it.close()
        }
        return realPath
    }

    suspend fun getProducts(): MutableList<Product> {
        return productsAPI.getProducts("products/loadProducts", authHeader).body() ?: mutableListOf()
    }
    suspend fun updateProduct(product: ProductForInsert, productId: String): Boolean {
        return productsAPI.updateProduct(productId, product, authHeader).body() ?: false
    }

}