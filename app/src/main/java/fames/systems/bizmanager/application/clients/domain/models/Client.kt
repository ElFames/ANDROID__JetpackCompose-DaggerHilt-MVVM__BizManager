package fames.systems.bizmanager.application.clients.domain.models

import fames.systems.bizmanager.domain.models.Purchase
import kotlinx.serialization.Serializable

@Serializable
data class Client(
    val id: Int,
    var name: String,
    var email: String,
    var phone: String,
    var address: String,
    val purchases: MutableList<Purchase>
)