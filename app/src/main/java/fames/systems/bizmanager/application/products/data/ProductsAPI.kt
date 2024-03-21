package fames.systems.bizmanager.application.products.data

import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.application.products.domain.models.ProductForInsert
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Url

interface ProductsAPI {
    @Headers("Content-Type: application/json")
    @POST("products/newProduct")
    suspend fun newProduct(
        @Body newProduct: ProductForInsert,
        @Header("Authorization") authHeader: String
    ): Response<Int>

    @Multipart
    @POST("products/{productId}/uploadImage")
    suspend fun newProductImage(
        @Path("productId") productId: String,
        @Part file: MultipartBody.Part,
        @Header("Authorization") authHeader: String
    ): Response<Unit>

    @GET
    suspend fun getProducts(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<MutableList<Product>>

    @Headers("Content-Type: application/json")
    @PUT("products/updateProduct/{productId}")
    suspend fun updateProduct(
        @Path("productId") productId: String,
        @Body newProduct: ProductForInsert,
        @Header("Authorization") authHeader: String
    ): Response<Boolean>


}
