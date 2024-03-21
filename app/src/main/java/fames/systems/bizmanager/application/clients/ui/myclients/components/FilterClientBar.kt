package fames.systems.bizmanager.application.clients.ui.myclients.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.R
import fames.systems.bizmanager.infrastructure.navigation.routes.AppScreens
import fames.systems.bizmanager.infrastructure.resources.buttonColor

@Composable
fun FilterClientBar(
    showRanking: Boolean,
    clientToSearch: String,
    navController: NavHostController,
    onUserToSearchChange: (String) -> Unit,
    changeShowRankingState: () -> Unit,
    getRankingClients: () -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item { ClientSearcher(clientToSearch, onUserToSearchChange) }
        item {
            ShowRankingButton(showRanking, { changeShowRankingState() }, { getRankingClients() })
            AddClientButton(navController)
        }
    }
}

@Composable
private fun ShowRankingButton(showRanking: Boolean, changeShowRankingState: () -> Unit, getRankingClients: () -> Unit) {
    val containerColor = if (showRanking) buttonColor else Color.LightGray

    Card(
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = CircleShape
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .size(32.dp)
                .clickable {
                    changeShowRankingState()
                    getRankingClients()
                },
            painter = painterResource(id = R.drawable.ranking_icon),
            contentDescription = "info icon"
        )
    }
}

@Composable
private fun AddClientButton(navController: NavHostController) {
    Card(
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        shape = CircleShape
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .size(32.dp)
                .clickable { navController.navigate(AppScreens.NewClientScreen.route) },
            painter = painterResource(id = R.drawable.add_client_icon),
            contentDescription = "info icon"
        )
    }
}