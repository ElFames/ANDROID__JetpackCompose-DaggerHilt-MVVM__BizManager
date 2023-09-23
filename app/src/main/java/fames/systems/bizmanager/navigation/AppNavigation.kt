package fames.systems.bizmanager.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fames.systems.bizmanager.application.auth.ui.auth.AuthScreen
import fames.systems.bizmanager.application.auth.ui.login.LoginScreen
import fames.systems.bizmanager.application.auth.ui.login.LoginViewModel
import fames.systems.bizmanager.application.auth.ui.register.RegisterScreen
import fames.systems.bizmanager.application.auth.ui.register.RegisterViewModel
import fames.systems.bizmanager.application.clients.ui.ClientsScreen
import fames.systems.bizmanager.application.clients.ui.ClientsViewModel
import fames.systems.bizmanager.application.dashboard.ui.DashboardScreen
import fames.systems.bizmanager.application.dashboard.ui.DashboardViewModel
import fames.systems.bizmanager.application.products.ui.ProductViewModel
import fames.systems.bizmanager.application.products.ui.ProductsScreen
import fames.systems.bizmanager.application.settings.ui.SettingsScreen
import fames.systems.bizmanager.application.settings.ui.SettingsViewModel
import fames.systems.bizmanager.application.tpvpos.ui.TpvPosScreen
import fames.systems.bizmanager.application.tpvpos.ui.TpvPosViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.DashboardScreen.route,
    ) {
        // AUTH
        composable(AppScreens.AuthScreen.route) {
            AuthScreen(navController)
        }
        composable(AppScreens.LoginScreen.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(viewModel, navController)
        }
        composable(AppScreens.RegisterScreen.route) {
            val viewModel = hiltViewModel<RegisterViewModel>()
            RegisterScreen(viewModel, navController)
        }

        // DASHBOARD
        composable(AppScreens.DashboardScreen.route) {
            val viewModel = hiltViewModel<DashboardViewModel>()
            DashboardScreen(viewModel, navController)
        }

        // CLIENTS
        composable(AppScreens.ClientsScreen.route) {
            val viewModel = hiltViewModel<ClientsViewModel>()
            ClientsScreen(viewModel, navController)
        }

        // TPV-POS
        composable(AppScreens.TpvPosScreen.route) {
            val viewModel = hiltViewModel<TpvPosViewModel>()
            TpvPosScreen(viewModel, navController)
        }

        // PRODUCTS
        composable(AppScreens.ProductsScreen.route) {
            val viewModel = hiltViewModel<ProductViewModel>()
            ProductsScreen(viewModel, navController)
        }

        // SETTINGS
        composable(AppScreens.SettingsScreen.route) {
            val viewModel = hiltViewModel<SettingsViewModel>()
            SettingsScreen(viewModel, navController)
        }
    }
}