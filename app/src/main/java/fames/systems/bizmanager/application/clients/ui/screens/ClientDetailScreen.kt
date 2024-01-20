package fames.systems.bizmanager.application.clients.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.clients.ui.components.ClientDataContainer
import fames.systems.bizmanager.application.clients.ui.components.clients.LastPurchases
import fames.systems.bizmanager.application.clients.ui.viewmodel.ClientDetailViewModel
import fames.systems.bizmanager.infrastructure.resources.buttonColor

@Composable
fun ClientDetailScreen(
    viewModel: ClientDetailViewModel,
    navController: NavHostController,
    clientId: String
) {
    val client by viewModel.client.observeAsState()
    val format = viewModel.getFormat()
    viewModel.getClient(clientId)

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier.fillMaxSize(),
        shape = RectangleShape
    ) {
        InsertClientDetailTitle(title = "Detalles del cliente $clientId", navController)
        ClientDataContainer(client)
        LastPurchases(client, format)
    }
}

@Composable
fun CancelButton(navController: NavHostController, color: Color) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back_button",
            modifier = Modifier
                .size(55.dp)
                .clickable { navController.popBackStack() }
                .padding(16.dp),
            tint = color
        )
}

@Composable
fun InsertClientDetailTitle(title: String, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CancelButton(navController = navController, Color.White)
        Text(
            modifier = Modifier.padding(16.dp),
            text = title,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic
            ),
            color = buttonColor
        )
    }
}

