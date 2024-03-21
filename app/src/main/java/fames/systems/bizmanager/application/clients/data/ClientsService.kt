package fames.systems.bizmanager.application.clients.data

import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.clients.domain.models.ClientToInsert
import fames.systems.bizmanager.application.clients.domain.models.ClientToUpdate
import fames.systems.bizmanager.domain.usecase.Session
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientsService @Inject constructor(
    private val session: Session,
    private val clientsAPI: ClientsAPI
) {
    private val authHeader = "Bearer ${session.getCurrentToken()}"

    suspend fun loadClients(): MutableList<Client> {
        return clientsAPI.loadClients("clients/loadAll", authHeader).body() ?: mutableListOf()
    }

    suspend fun insertClient(
        name: String,
        email: String,
        phone: String,
        address: String
    ): Pair<Boolean, String> {
        val clientToInsert = ClientToInsert(name, email, phone, address)
        // response es el id del cliente añadido en el servidor para poder añadirlo en local
        val response = clientsAPI.insertClient(clientToInsert, authHeader).body()
        return if (response != null) {
            Pair(true, response)
        } else {
            Pair(false, "")
        }

    }

    suspend fun updateClient(clientToUpdate: ClientToUpdate): Boolean {
        return clientsAPI.updateClient(clientToUpdate, authHeader).body() ?: false
    }

}