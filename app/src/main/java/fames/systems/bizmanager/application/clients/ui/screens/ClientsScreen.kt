package fames.systems.bizmanager.application.clients.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.shared.HorizontalLine
import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.clients.ui.components.clients.ClientRankingContainer
import fames.systems.bizmanager.application.clients.ui.components.clients.FilterClientBar
import fames.systems.bizmanager.application.clients.ui.viewmodel.ClientsViewModel
import fames.systems.bizmanager.application.dashboard.ui.screens.InsertTitle
import fames.systems.bizmanager.infrastructure.navigation.screenRoutes.AppScreens

@Composable
fun ClientsScreen(viewModel: ClientsViewModel, navController: NavHostController) {
    var showRanking by rememberSaveable { mutableStateOf(false) }
    val clientToSearch by viewModel.clientToSearch.observeAsState(initial = "")
    val clients by viewModel.clients.observeAsState(initial = mutableListOf())
    val clientRanking by viewModel.clientRanking.observeAsState(initial = mutableListOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(bottom = 70.dp)
    ) {
        InsertTitle("Lista de Clientes")
        HorizontalLine(color = Color.LightGray)
        FilterClientBar(
            showRanking,
            clientToSearch,
            navController,
            { viewModel.onClientToSearchChange(it) },
            { showRanking = !showRanking },
            { viewModel.getClientRanking() }
        )
            if (showRanking) {
                ClientRankingContainer(clientRanking)
            }
            ListGridClients(clients, navController)
    }
}

@Composable
fun ListGridClients(clients: List<Client>, navController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        clients.forEach { client ->
            item {
                ClientInfoContainer(Color.White, client, navController)
            }
        }
    }
}

@Composable
fun ClientInfoContainer(containerColor: Color, client: Client, navController: NavHostController) {
    Card(
        modifier = Modifier.padding(10.dp).clickable { navController.navigate(route = AppScreens.ClientDetailScreen.route + "/${client.id}") },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = Color.Black
        ),
        border = BorderStroke(width = 2.dp, color = Color.Gray),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = client.name, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = client.phone, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Nº Compras: ${client.purchases.size}",
                fontFamily = FontFamily.Serif,
                color = Color.LightGray
            )
        }
    }
}
