package fames.systems.bizmanager.domain

import fames.systems.bizmanager.application.products.domain.models.Product


data class Purchase(val id: String, val product: Product, val dateTime: DateTime)