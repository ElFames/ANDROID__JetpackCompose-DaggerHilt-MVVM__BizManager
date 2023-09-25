package fames.systems.bizmanager.application.clients.infrastructure

import fames.systems.bizmanager.application.clients.domain.models.Client
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface ClientsAPI {
    @GET
    suspend fun loadClients(
        @Url url: String,
        @Header("Authorization") authHeader: String
    ): Response<MutableList<Client>>

}