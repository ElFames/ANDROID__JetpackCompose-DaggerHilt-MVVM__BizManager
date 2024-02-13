package fames.systems.bizmanager.application.clients.ui.clientdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.register.components.AlertDialogError
import fames.systems.bizmanager.application.auth.ui.shared.HorizontalLine
import fames.systems.bizmanager.application.auth.ui.shared.ShowLoadingScreen
import fames.systems.bizmanager.application.clients.ui.clientdetail.components.ClientDataContainer
import fames.systems.bizmanager.application.clients.ui.clientdetail.components.ClientDetailTitle
import fames.systems.bizmanager.application.clients.ui.clientdetail.components.LastPurchases
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.navigation.screenRoutes.BottomBarScreens

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
    client?.let { viewModel.setLastClientViewed(client!!) }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier.fillMaxSize(),
        shape = RectangleShape
    ) {
        ClientDetailTitle(title = "Detalles del cliente ${client?.id}", navController)
        Row(modifier = Modifier.fillMaxWidth().padding(13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Image(modifier = Modifier
                .border(1.dp, Color.DarkGray, shape = MaterialTheme.shapes.medium)
                .padding(5.dp)
                .clickable { /* navegar a la pantalla de editar cliente */ },
                imageVector = Icons.Default.Edit,
                contentDescription = "edit_icon"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(modifier = Modifier
                .border(1.dp, Color.DarkGray, shape = MaterialTheme.shapes.medium)
                .padding(5.dp)
                .clickable { navController.navigate(BottomBarScreens.TpvPosScreen.route) },
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "init_purchase_icon"
            )
        }
        ClientDataContainer(client)
        LastPurchases(client)
    }
}