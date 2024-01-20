package fames.systems.bizmanager.infrastructure.navigation.bottombar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import fames.systems.bizmanager.infrastructure.navigation.screenRoutes.BottomBarScreens
import fames.systems.bizmanager.infrastructure.resources.buttonColor

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreens.DashboardScreen,
        BottomBarScreens.ClientsScreen,
        BottomBarScreens.TpvPosScreen,
        BottomBarScreens.ProductsScreen,
        BottomBarScreens.SettingsScreen,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomAppBar(
            modifier = Modifier,
            containerColor = Color.DarkGray,
            tonalElevation = 4.dp
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any { screen.route == it.route } == true,
        icon = { Icon(imageVector = screen.icon, contentDescription = "nav_icon") },
        modifier = Modifier,
        enabled = true,
        label = { Text(text = screen.title) },
        alwaysShowLabel = true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.Black,
            selectedTextColor = buttonColor,
            indicatorColor = buttonColor,
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray
        )
    )
}