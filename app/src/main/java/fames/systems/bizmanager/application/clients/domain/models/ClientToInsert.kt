package fames.systems.bizmanager.application.clients.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ClientToInsert(val name: String, val email: String, val phone: String, val address: String)