package fames.systems.bizmanager.domain.usecase

import fames.systems.bizmanager.application.clients.domain.ClientsRepository
import fames.systems.bizmanager.application.dashboard.domain.DashboardRepository
import fames.systems.bizmanager.application.products.domain.ProductsRepository
import javax.inject.Inject

class LoadData @Inject constructor(
    private val clientsRepository: ClientsRepository,
    private val dashboardRepository: DashboardRepository,
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke() {
        clientsRepository.loadClients()
        dashboardRepository.loadStatistics()
        productsRepository.loadProducts()
    }
}