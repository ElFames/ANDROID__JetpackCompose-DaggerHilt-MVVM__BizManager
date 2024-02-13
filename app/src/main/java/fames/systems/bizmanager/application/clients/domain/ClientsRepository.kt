package fames.systems.bizmanager.application.clients.domain

import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.clients.data.ClientsService
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
            /**
            clients = clientsService.loadClients()
             **/

            clients = clientsTest
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
        /**
        val reponseMessage = clientsService.insertClient(name, email, phone, address)

        return if (reponseMessage == "No hay conexión con el servidor" || reponseMessage == "El telefono ya está en uso") {
        Pair(false, reponseMessage)
        } else {
        val clientAdded = Client(
        reponseMessage.toInt(),
        name,
        email,
        phone,
        address,
        mutableListOf()
        )
        clients.add(clientAdded)
        originalClients.add(clientAdded)
        Pair(true, reponseMessage)
        }**/

        val id = (50..9999).random()
        val testClient =
            Client((id..9999999).random(), name, email, phone, address, mutableListOf())
        clients.add(testClient)
        originalClients.add(testClient)
        return Pair(true, testClient.id.toString())
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

}

val clientsTest = MutableList(14) { clientId ->
    Client(
        id = clientId,
        name = "Cliente $clientId",
        email = "cliente$clientId@example.com",
        phone = "123-456-7890",
        address = "jacint verdaguer 7A 4-1",
        purchases = MutableList(20) { purchaseId ->
            Purchase(
                id = UUID.randomUUID().toString(),
                products = MutableList(3) { productId ->
                    Product(
                        id = UUID.randomUUID().toString(),
                        name = "Producto $purchaseId",
                        sellPrice = 10.0 + clientId * 5,
                        expenses = MutableList(5) {
                            SubProduct(
                                id = UUID.randomUUID().toString(),
                                name = "SubProducto $clientId",
                                costPrice = 5.0,
                                quantity = clientId + 1,
                                quantityCurrency = if (purchaseId % 2 == 0) "g" else "ml"
                            )
                        }
                    )
                },
                dateTime = getCurrentDateTime(),
                totalPrice = 35.50,
                null
            )
        }
    )
}