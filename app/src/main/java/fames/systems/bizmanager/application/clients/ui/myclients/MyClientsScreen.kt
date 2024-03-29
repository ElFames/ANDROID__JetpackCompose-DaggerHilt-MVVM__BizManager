package fames.systems.bizmanager.application.clients.ui.myclients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.clients.ui.myclients.components.ClientInfoContainer
import fames.systems.bizmanager.application.clients.ui.myclients.components.ClientRankingContainer
import fames.systems.bizmanager.application.clients.ui.myclients.components.FilterClientBar
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.utils.dialogs.BasicAlertDialog
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.HorizontalLine
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.InsertTitle
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.ShowLoadingView

@Composable
fun MyClientsScreen(viewModel: MyClientsViewModel, navController: NavHostController) {
    val uiState: UiState by viewModel.uiState.collectAsState(UiState.IDLE)

    when(uiState) {
        UiState.IDLE -> viewModel.loadClients()
        UiState.LOADING -> ShowLoadingView()
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se ha podido recuperar los clientes",
            color = Color.Red,
            onConfirmation = {  }
        )
        UiState.SUCCESS -> MyClientsScreenContent(viewModel, navController)
        UiState.PLUS -> TODO()
    }

}

@Composable
fun MyClientsScreenContent(viewModel: MyClientsViewModel, navController: NavHostController) {
    var showRanking by rememberSaveable { mutableStateOf(false) }
    val clientToSearch by viewModel.clientToSearch.observeAsState(initial = "")
    val clients by viewModel.clients.observeAsState(initial = mutableListOf())
    val clientRanking by viewModel.clientRanking.observeAsState(initial = mutableListOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(bottom = 70.dp)
    ) {
        InsertTitle("Clientes")
        FilterClientBar(
            showRanking,
            clientToSearch,
            navController,
            { viewModel.onClientToSearchChange(it) },
            { showRanking = !showRanking },
            { viewModel.getClientRanking() }
        )
        if (showRanking) ClientRankingContainer(clientRanking)
        ListGridClients(clients, navController)
    }
}

@Composable
fun ListGridClients(clients: List<Client>, navController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        clients.forEach { client ->
            item {
                ClientInfoContainer(Color.White, client, navController)
            }
        }
    }
}
