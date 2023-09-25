package fames.systems.bizmanager.application.auth.infrastructure

import fames.systems.bizmanager.application.auth.domain.models.MyUser
import fames.systems.bizmanager.application.auth.domain.models.UserForRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Url

interface AuthAPI {

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun getNewToken(
        @Body user: MyUser
    ): Response<HashMap<String, String>>

    @Headers("Content-Type: application/json")
    @POST("auth/register")
    suspend fun registerNewUser(
        @Body user: UserForRegister
    ): Response<Boolean>

    @Headers("Content-Type: application/json")
    @PUT("auth/changePassword/{password}")
    suspend fun changePassword(
        @Path("password") newPassword: String,
        @Header("Authorization") authHeader: String
    ): Response<Boolean>

    @Headers("Content-Type: application/json")
    @PUT("auth/changeEmail/{email}")
    suspend fun changeEmail(
        @Path("email") newEmail: String,
        @Header("Authorization") authHeader: String
    ): Response<Boolean>

    @GET
    suspend fun audience(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<Boolean>
}