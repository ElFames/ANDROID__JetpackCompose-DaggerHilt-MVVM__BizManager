package fames.systems.bizmanager.application.products.infrastructure

import fames.systems.bizmanager.domain.Session
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsService @Inject constructor(
    private val sessionService: Session
) {
    private val myToken get() = sessionService.getCurrentToken()


}