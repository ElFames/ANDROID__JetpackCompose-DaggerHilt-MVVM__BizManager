package fames.systems.bizmanager.navigation.screenRoutes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens(val route: String, val title: String, val icon: ImageVector) {
    object DashboardScreen: BottomBarScreens(route = "dashboard", title = "Dashboard", icon = Icons.Outlined.Info)
    object ClientsScreen: BottomBarScreens(route = "clients", title = "Clientes", icon = Icons.Outlined.Person)
    object TpvPosScreen: BottomBarScreens(route = "tpv_pos", title = "TPV", icon = Icons.Outlined.ShoppingCart)
    object ProductsScreen: BottomBarScreens(route = "products", title = "Productos", icon = Icons.Outlined.Home)
    object SettingsScreen: BottomBarScreens(route = "settings", title = "Ajustes", icon = Icons.Outlined.Settings)
}