package fames.systems.bizmanager.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.R
import fames.systems.bizmanager.resources.authBackgroundColor

@Composable
fun SimpleNavMenu(navController: NavHostController) {
    var navMenuIsOpen by rememberSaveable { mutableStateOf(false) }
    Column(modifier = Modifier.padding(16.dp)) {
        NavMenuButton { navMenuIsOpen = !navMenuIsOpen }
        NavMenuContainer(navMenuIsOpen, navController) { navMenuIsOpen = !navMenuIsOpen }
    }
}

@Composable
fun NavMenuButton(changeMenuState: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = authBackgroundColor)
    ) {
        Image(
            modifier = Modifier
                .size(35.dp)
                .padding(5.dp)
                .clickable { changeMenuState() },
            painter = painterResource(R.drawable.menu_icon),
            contentDescription = "menu icon",
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun NavMenuContainer(navMenuIsOpen: Boolean, navController: NavHostController, changeMenuState: () -> Unit) {
    if (navMenuIsOpen) {
        Card(
            shape = MaterialTheme.shapes.small,
            colors = CardDefaults.cardColors(
                containerColor = authBackgroundColor,
                contentColor = Color.White
            )
        ) {
            DestinationButton("Estadísticas", changeMenuState) { navController.navigate(AppScreens.DashboardScreen.route) }
            DestinationButton("Clientes", changeMenuState) { navController.navigate(AppScreens.ClientsScreen.route)  }
            DestinationButton("Punto de Venta", changeMenuState) { navController.navigate(AppScreens.TpvPosScreen.route) }
            DestinationButton("Productos-Servicios", changeMenuState) { navController.navigate(AppScreens.ProductsScreen.route)  }
            DestinationButton("Ajustes", changeMenuState) { navController.navigate(AppScreens.SettingsScreen.route)  }
        }
    }
}

@Composable
fun DestinationButton(destinationText: String, changeMenuState: () -> Unit, navigate: () -> Unit) {
    Text(modifier = Modifier
        .padding(16.dp)
        .clickable {
            changeMenuState()
            navigate()
        },
        text = destinationText
    )
}
