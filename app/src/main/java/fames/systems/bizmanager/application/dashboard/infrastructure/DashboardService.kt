package fames.systems.bizmanager.application.dashboard.infrastructure

import fames.systems.bizmanager.domain.Session
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardService @Inject constructor(
    private val sessionService: Session
) {
    private val myToken get() = sessionService.getCurrentToken()


}