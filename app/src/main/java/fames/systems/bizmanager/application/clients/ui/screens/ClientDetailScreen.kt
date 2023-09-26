package fames.systems.bizmanager.application.clients.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.clients.ui.components.newclient.CancelButton
import fames.systems.bizmanager.application.clients.ui.viewmodel.ClientDetailViewModel

@Composable
fun ClientDetailScreen(viewModel: ClientDetailViewModel, navController: NavHostController, clientId: String) {
    val client by viewModel.client.observeAsState()
    viewModel.getClient(clientId)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CancelButton(navController = navController)
            Text(
                text = "Detalles del Cliente ${client?.id}",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontSize = 22.sp,
                fontStyle = FontStyle.Italic
            )
        }
        item {
            Text(
                text = client?.name ?: "",
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
            )
            Text(
                text = client?.phone ?: "",
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
            )
            Text(
                text = client?.email ?: "",
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
            )
            Text(
                text = client?.address ?: "",
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
            )
        }
        item {

        }
    }
}