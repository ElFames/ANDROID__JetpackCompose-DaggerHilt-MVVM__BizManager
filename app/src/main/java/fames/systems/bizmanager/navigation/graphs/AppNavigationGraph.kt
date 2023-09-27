package fames.systems.bizmanager.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fames.systems.bizmanager.application.auth.ui.auth.AuthScreen
import fames.systems.bizmanager.application.auth.ui.login.LoginScreen
import fames.systems.bizmanager.application.auth.ui.login.LoginViewModel
import fames.systems.bizmanager.application.auth.ui.register.RegisterScreen
import fames.systems.bizmanager.application.auth.ui.register.RegisterViewModel
import fames.systems.bizmanager.application.clients.ui.screens.ClientDetailScreen
import fames.systems.bizmanager.application.clients.ui.screens.ClientsScreen
import fames.systems.bizmanager.application.clients.ui.screens.NewClientScreen
import fames.systems.bizmanager.application.clients.ui.viewmodel.ClientDetailViewModel
import fames.systems.bizmanager.application.clients.ui.viewmodel.ClientsViewModel
import fames.systems.bizmanager.application.clients.ui.viewmodel.NewClientViewModel
import fames.systems.bizmanager.application.dashboard.ui.screens.DashboardScreen
import fames.systems.bizmanager.application.dashboard.ui.viewmodel.DashboardViewModel
import fames.systems.bizmanager.application.products.ui.ProductViewModel
import fames.systems.bizmanager.application.products.ui.ProductsScreen
import fames.systems.bizmanager.application.settings.ui.SettingsScreen
import fames.systems.bizmanager.application.settings.ui.SettingsViewModel
import fames.systems.bizmanager.application.tpvpos.ui.TpvPosScreen
import fames.systems.bizmanager.application.tpvpos.ui.TpvPosViewModel
import fames.systems.bizmanager.navigation.screenRoutes.AppScreens
import fames.systems.bizmanager.navigation.screenRoutes.BottomBarScreens

@Composable
fun AppNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.DashboardScreen.route,
    ) {
        // AUTH
        composable(AppScreens.AuthScreen.route) {
            AuthScreen(navController)
        }
        composable(AppScreens.LoginScreen.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(viewModel, navController) { navController.navigate(BottomBarScreens.DashboardScreen.route) }
        }
        composable(AppScreens.RegisterScreen.route) {
            val viewModel = hiltViewModel<RegisterViewModel>()
            RegisterScreen(viewModel, navController)
        }

        // DASHBOARD
        composable(BottomBarScreens.DashboardScreen.route) {
            val viewModel = hiltViewModel<DashboardViewModel>()
            DashboardScreen(viewModel)
        }

        // CLIENTS
        composable(BottomBarScreens.ClientsScreen.route) {
            val viewModel = hiltViewModel<ClientsViewModel>()
            ClientsScreen(viewModel, navController)
        }
        composable(AppScreens.NewClientScreen.route) {
            val viewModel = hiltViewModel<NewClientViewModel>()
            NewClientScreen(viewModel, navController)
        }
        composable(AppScreens.ClientDetailScreen.route + "/{clientId}") { navBackStackEntry ->
            val clientId = navBackStackEntry.arguments?.getString("clientId") ?: ""
            val viewModel = hiltViewModel<ClientDetailViewModel>()
            ClientDetailScreen(viewModel, navController, clientId)
        }

        // TPV-POS
        composable(BottomBarScreens.TpvPosScreen.route) {
            val viewModel = hiltViewModel<TpvPosViewModel>()
            TpvPosScreen(viewModel, navController)
        }

        // PRODUCTS
        composable(BottomBarScreens.ProductsScreen.route) {
            val viewModel = hiltViewModel<ProductViewModel>()
            ProductsScreen(viewModel, navController)
        }

        // SETTINGS
        composable(BottomBarScreens.SettingsScreen.route) {
            val viewModel = hiltViewModel<SettingsViewModel>()
            SettingsScreen(viewModel, navController)
        }
    }
}