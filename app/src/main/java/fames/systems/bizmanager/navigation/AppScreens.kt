package fames.systems.bizmanager.navigation

sealed class AppScreens(val route: String) {
    object AuthScreen: AppScreens(route = "auth")
    object RegisterScreen: AppScreens(route = "register")
    object LoginScreen: AppScreens(route = "login")
    object DashboardScreen: AppScreens(route = "dashboard")
    object ClientsScreen: AppScreens(route = "clients")
    object TpvPosScreen: AppScreens(route = "tpv_pos")
    object ProductsScreen: AppScreens(route = "products")
    object SettingsScreen: AppScreens(route = "settings")
}