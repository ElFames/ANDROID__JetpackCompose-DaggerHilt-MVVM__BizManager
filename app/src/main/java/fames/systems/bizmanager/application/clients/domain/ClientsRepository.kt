package fames.systems.bizmanager.application.clients.domain

import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.clients.data.ClientsService
import fames.systems.bizmanager.application.clients.domain.models.ClientToUpdate
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.domain.models.Purchase
import fames.systems.bizmanager.application.products.domain.models.SubProduct
import fames.systems.bizmanager.domain.models.getCurrentDateTime
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientsRepository @Inject constructor(
    private val clientsService: ClientsService
) {
    private var clients = mutableListOf<Client>()
    private var lastClientViewed: Client? = null
    private var filteredClients = mutableListOf<Client>()
    private var originalClients = mutableListOf<Client>()

    fun searchClient(clientToSearch: String): MutableList<Client> {
        filteredClients.clear()
        return if (clientToSearch.isEmpty()) {
            filteredClients.addAll(originalClients)
            filteredClients
        } else {
            val lowerCaseQuery = clientToSearch.lowercase()
            for (client in originalClients) {
                if (client.name.lowercase().contains(lowerCaseQuery) ||
                    client.email.lowercase().contains(lowerCaseQuery) ||
                    client.phone.lowercase().contains(lowerCaseQuery) ||
                    client.address.lowercase().contains(lowerCaseQuery)
                ) {
                    filteredClients.add(client)
                }
            }
            filteredClients
        }
    }

    fun getClients() = clients

    suspend fun loadClients(): MutableList<Client> {
        return clients.ifEmpty {
            clients = clientsService.loadClients()
            //clients = clientsTest
            originalClients.addAll(clients)
            clients
        }
    }

    fun getClientRanking(): List<Pair<String, Double>> {
        val clientRanking = mutableListOf<Pair<String, Double>>()
        clients.forEach { client ->
            val totalProfit = client.purchases.sumOf { purchase ->
                purchase.products.sumOf { product ->
                    product.sellPrice - product.expenses.sumOf { subProduct ->
                        subProduct.costPrice
                    }
                }
            }
            clientRanking.add(Pair(client.name, totalProfit))
        }
        return clientRanking.sortedByDescending { it.second }
    }

    suspend fun newClient(
        name: String,
        email: String,
        phone: String,
        address: String
    ): Pair<Boolean, String> {
        val reponse = clientsService.insertClient(name, email, phone, address)

        return if (!reponse.first) {
            Pair(false, "No hay conexión con el servidor")
        } else {
            val clientAdded = Client(
                reponse.second.toInt(),
                name,
                email,
                phone,
                address,
                mutableListOf()
            )
            clients.add(clientAdded)
            originalClients.add(clientAdded)
            Pair(true, "Cliente añadido con éxito")
        }
    }

    fun getClient(clientId: String): Client {
        val client = clients.find { it.id.toString() == clientId }
        return client!!
    }

    fun getLastClientView(): Client? {
        return lastClientViewed
    }

    fun setLastClientView(client: Client?) {
        lastClientViewed = client
    }

    suspend fun updateClient(
        clientId: Int,
        name: String,
        email: String,
        phone: String,
        address: String
    ): Boolean {
        val clientToUpdate = ClientToUpdate(clientId.toString(), name, email, phone, address)
        val response = clientsService.updateClient(clientToUpdate)
        return if (response) {
            val index = clients.indexOfFirst { it.id == clientId }
            clients[index].apply {
                this.name = name
                this.email = email
                this.phone = phone
                this.address = address
            }
            val originalIndex = originalClients.indexOfFirst { it.id == clientId }
            originalClients[originalIndex].apply {
                this.name = name
                this.email = email
                this.phone = phone
                this.address = address
            }

            true
        } else {
            false
        }
    }

}