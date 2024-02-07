package fames.systems.bizmanager.application.clients.ui.clientdetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.register.components.AlertDialogError
import fames.systems.bizmanager.application.auth.ui.shared.ShowLoadingScreen
import fames.systems.bizmanager.application.clients.ui.clientdetail.components.ClientDataContainer
import fames.systems.bizmanager.application.clients.ui.clientdetail.components.ClientDetailTitle
import fames.systems.bizmanager.application.clients.ui.clientdetail.components.LastPurchases
import fames.systems.bizmanager.domain.models.UiState

@Composable
fun ClientDetailScreen(
    viewModel: ClientDetailViewModel,
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
        UiState.SUCCESS -> ShowClientDetailScreen(viewModel, navController)
    }
}

@Composable
fun ShowClientDetailScreen(
    viewModel: ClientDetailViewModel,
    navController: NavHostController
) {
    val client by viewModel.client.observeAsState()
    
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier.fillMaxSize(),
        shape = RectangleShape
    ) {
        ClientDetailTitle(title = "Detalles del cliente ${client?.id}", navController)
        ClientDataContainer(client)
        LastPurchases(client)
    }
}