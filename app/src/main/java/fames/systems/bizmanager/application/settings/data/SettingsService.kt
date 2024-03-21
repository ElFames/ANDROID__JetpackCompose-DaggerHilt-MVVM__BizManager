package fames.systems.bizmanager.application.settings.data

import fames.systems.bizmanager.domain.usecase.Session
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsService @Inject constructor(
    private val sessionService: Session
) {
    private val myToken get() = sessionService.getCurrentToken()


}