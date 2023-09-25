package fames.systems.bizmanager.application.clients.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import fames.systems.bizmanager.application.clients.ui.components.ClientRankingContainer
import fames.systems.bizmanager.application.clients.ui.components.FilterClientBar
import fames.systems.bizmanager.application.clients.ui.viewmodel.ClientsViewModel
import fames.systems.bizmanager.application.dashboard.ui.screens.InsertTitle
import fames.systems.bizmanager.navigation.NavMenu
import fames.systems.bizmanager.navigation.NavMenuButton
import fames.systems.bizmanager.resources.buttonColor
import fames.systems.bizmanager.resources.cardContainerColor
import fames.systems.bizmanager.resources.orange

@Composable
fun ClientsScreen(viewModel: ClientsViewModel, navController: NavHostController) {
    var navMenuIsOpen by rememberSaveable { mutableStateOf(false) }
    var showRanking by rememberSaveable { mutableStateOf(false) }
    val clientToSearch by viewModel.clientToSearch.observeAsState(initial = "")
    val clients by viewModel.clients.observeAsState(initial = mutableListOf())
    val clientRanking by viewModel.clientRanking.observeAsState(initial = mutableListOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
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
        ListGridClients(clients)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        NavMenuButton { navMenuIsOpen = !navMenuIsOpen }
        NavMenu(navMenuIsOpen, navController)
    }
}

@Composable
fun ListGridClients(clients: List<Client>) {
    val containerColors = listOf(cardContainerColor, orange, buttonColor)
    var i = 0
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        clients.forEach { client ->
            val containerColor = containerColors[i]
            if (i == containerColors.size - 1) i = 0 else i++
            item {
                ClientInfoContainer(containerColor, client)
            }
        }
    }
}

@Composable
fun ClientInfoContainer(containerColor: Color, client: Client) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = Color.White
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
            Text(text = "Nº Compras: ${client.purchases.size}", fontFamily = FontFamily.Serif, color = Color.LightGray)
        }
    }
}
