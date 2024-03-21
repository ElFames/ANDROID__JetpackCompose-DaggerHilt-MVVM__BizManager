package fames.systems.bizmanager.application.dashboard.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class MyStatistics(
    val numOfSales: Int,
    val profit: Double,
    val expenses: Double,
    val income: Double
)