package fames.systems.bizmanager.application.clients.data

import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.clients.domain.models.ClientToInsert
import fames.systems.bizmanager.application.clients.domain.models.ClientToUpdate
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Url

interface ClientsAPI {
    @GET
    suspend fun loadClients(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<MutableList<Client>>

    @Headers("Content-Type: application/json")
    @PUT("client/insert")
    suspend fun insertClient(
        @Body clientToInsert: ClientToInsert,
        @Header("Authorization") authHeader: String
    ): Response<String>

    @Headers("Content-Type: application/json")
    @PUT("client/update")
    suspend fun updateClient(
        @Body clientToUpdate: ClientToUpdate,
        @Header("Authorization") authHeader: String
    ): Response<Boolean>

}