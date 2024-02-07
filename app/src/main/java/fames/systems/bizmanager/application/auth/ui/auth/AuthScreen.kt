package fames.systems.bizmanager.application.auth.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.auth.components.NavigateLoginScreenButton
import fames.systems.bizmanager.application.auth.ui.auth.components.NavigateRegisterScreenButton
import fames.systems.bizmanager.application.auth.ui.shared.HeaderImage
import fames.systems.bizmanager.infrastructure.resources.authBackgroundColor

@Composable
fun AuthScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier.background(color = authBackgroundColor).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            HeaderImage()
        }
        item {
            NavigateLoginScreenButton(navController)
            NavigateRegisterScreenButton(navController)
        }
    }
}