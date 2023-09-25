package fames.systems.bizmanager.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.R
import fames.systems.bizmanager.resources.authBackgroundColor


@Composable
fun NavMenu(navMenuIsOpen: Boolean, navController: NavHostController) {
    if (navMenuIsOpen) {
        Card(
            shape = MaterialTheme.shapes.small,
            colors = CardDefaults.cardColors(
                containerColor = authBackgroundColor,
                contentColor = Color.White
            )
        ) {
            Text(modifier = Modifier.clickable { navController.navigate(AppScreens.DashboardScreen.route) }.padding(16.dp), text = "Estadísticas")
            Text(modifier = Modifier.clickable { navController.navigate(AppScreens.ClientsScreen.route) }.padding(16.dp), text = "Clientes")
            Text(modifier = Modifier.clickable { navController.navigate(AppScreens.TpvPosScreen.route) }.padding(16.dp), text = "Punto de Venta")
            Text(modifier = Modifier.clickable { navController.navigate(AppScreens.ProductsScreen.route) }.padding(16.dp), text = "Productos-Servicios")
            Text(modifier = Modifier.clickable { navController.navigate(AppScreens.SettingsScreen.route) }.padding(16.dp), text = "Ajustes")
        }
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