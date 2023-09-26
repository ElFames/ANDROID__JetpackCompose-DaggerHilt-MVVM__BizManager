package fames.systems.bizmanager.navigation


sealed class AppScreens(val route: String) {
    object AuthScreen: AppScreens(route = "auth")
    object RegisterScreen: AppScreens(route = "register")
    object LoginScreen: AppScreens(route = "login")

    object DashboardScreen: AppScreens(route = "dashboard")
    object HistoryStatisticsScreen: AppScreens(route = "history_statistics")

    object ClientsScreen: AppScreens(route = "clients")
    object ClientDetailScreen: AppScreens(route = "detail_client")
    object NewClientScreen: AppScreens(route = "new_client")

    object TpvPosScreen: AppScreens(route = "tpv_pos")

    object ProductsScreen: AppScreens(route = "products")

    object SettingsScreen: AppScreens(route = "settings")
}