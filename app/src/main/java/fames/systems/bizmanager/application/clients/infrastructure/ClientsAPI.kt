package fames.systems.bizmanager.application.clients.infrastructure

import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.clients.domain.models.ClientToInsert
import retrofit2.Response
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
    @PUT("client/insert/{clientToInsert}")
    suspend fun insertClient(
        @Path("clientToInsert") clientToInsert: ClientToInsert,
        @Header("Authorization") authHeader: String
    ): Response<String>

}