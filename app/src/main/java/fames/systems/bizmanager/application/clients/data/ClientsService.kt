package fames.systems.bizmanager.application.clients.data

import androidx.compose.runtime.structuralEqualityPolicy
import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.clients.domain.models.ClientToInsert
import fames.systems.bizmanager.domain.Session
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

    suspend fun insertClient(name: String, email: String, phone: String, address: String): String {
        val clientToInsert = ClientToInsert(name, email, phone, address)
        return clientsAPI.insertClient(clientToInsert, authHeader).body() ?: "No hay conexión con el servidor"
    }

    fun updateClient(newClient: Client): Boolean {
        return true
    }

}