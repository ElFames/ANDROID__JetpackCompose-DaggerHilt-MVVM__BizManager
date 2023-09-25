package fames.systems.bizmanager.application.clients.domain.models

import fames.systems.bizmanager.domain.Purchase

data class Client(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val purchases: List<Purchase>
)