package fames.systems.bizmanager.infrastructure.navigation.graphs

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
import fames.systems.bizmanager.application.clients.ui.clientdetail.ClientDetailScreen
import fames.systems.bizmanager.application.clients.ui.myclients.MyClientsScreen
import fames.systems.bizmanager.application.clients.ui.newclient.NewClientScreen
import fames.systems.bizmanager.application.clients.ui.clientdetail.ClientDetailViewModel
import fames.systems.bizmanager.application.clients.ui.editclient.EditClientScreen
import fames.systems.bizmanager.application.clients.ui.editclient.EditClientViewModel
import fames.systems.bizmanager.application.clients.ui.myclients.MyClientsViewModel
import fames.systems.bizmanager.application.clients.ui.newclient.NewClientViewModel
import fames.systems.bizmanager.application.dashboard.ui.DashboardScreen
import fames.systems.bizmanager.application.dashboard.ui.DashboardViewModel
import fames.systems.bizmanager.application.products.ui.ProductViewModel
import fames.systems.bizmanager.application.products.ui.ProductsScreen
import fames.systems.bizmanager.application.settings.ui.SettingsScreen
import fames.systems.bizmanager.application.settings.ui.SettingsViewModel
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.PurchaseInvoicerScreen
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.PurchaseInvoicerViewModel
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.TpvPosScreen
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.TpvPosViewModel
import fames.systems.bizmanager.infrastructure.navigation.routes.AppScreens
import fames.systems.bizmanager.infrastructure.navigation.routes.BottomBarScreens

@Composable
fun AppNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.AuthScreen.route,
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
            val viewModel = hiltViewModel<MyClientsViewModel>()
            MyClientsScreen(viewModel, navController)
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
        composable(AppScreens.EditClientScreen.route + "/{clientId}") { navBackStackEntry ->
            val clientId = navBackStackEntry.arguments?.getString("clientId") ?: ""
            val viewModel = hiltViewModel<EditClientViewModel>()
            EditClientScreen(viewModel, navController, clientId)
        }

        // TPV-POS
        composable(BottomBarScreens.TpvPosScreen.route) {
            val viewModel = hiltViewModel<TpvPosViewModel>()
            TpvPosScreen(viewModel, navController)
        }
        composable(AppScreens.PurchaseInvoicerScreen.route) {
            val viewModel = hiltViewModel<PurchaseInvoicerViewModel>()
            PurchaseInvoicerScreen(viewModel, navController)
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