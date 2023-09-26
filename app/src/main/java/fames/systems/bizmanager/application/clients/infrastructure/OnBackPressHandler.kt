package fames.systems.bizmanager.application.clients.infrastructure

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class BackPressHandler(
    private val backDispatcher: OnBackPressedDispatcher,
    private val onBackPress: () -> Unit
) {
    var isEnabled by mutableStateOf(true)

    init {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isEnabled) {
                    onBackPress()
                }
            }
        }
        backDispatcher.addCallback(callback)
    }
}



