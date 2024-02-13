package fames.systems.bizmanager.application.clients.ui.editclient

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.register.components.AlertDialogError
import fames.systems.bizmanager.infrastructure.utils.ShowLoadingScreen
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.utils.ScreenTitleWithBackButton

@Composable
fun EditClientScreen(
    viewModel: EditClientViewModel,
    navController: NavHostController,
    clientId: String
) {
    val uiState by viewModel.uiState.collectAsState()

    when(uiState) {
        UiState.IDLE -> viewModel.getClient(clientId)
        UiState.LOADING -> ShowLoadingScreen()
        UiState.ERROR -> AlertDialogError(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se ha podido cargar el cliente",
            onConfirmation = { navController.popBackStack() }
        )
        UiState.SUCCESS -> {
            ScreenTitleWithBackButton(title = "Editar Información", navController = navController)
        }//ShowEditClientScreen(viewModel, navController)
    }
}