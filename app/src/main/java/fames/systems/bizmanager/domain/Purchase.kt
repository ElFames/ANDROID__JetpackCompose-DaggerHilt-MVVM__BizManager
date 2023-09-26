package fames.systems.bizmanager.domain

import fames.systems.bizmanager.application.products.domain.models.Product
import kotlinx.serialization.Serializable


@Serializable
data class Purchase(val id: String, val product: Product, val dateTime: DateTime)