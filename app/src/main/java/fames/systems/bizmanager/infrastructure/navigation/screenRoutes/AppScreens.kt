package fames.systems.bizmanager.infrastructure.navigation.screenRoutes


sealed class AppScreens(val route: String) {
    object AuthScreen: AppScreens(route = "auth")
    object RegisterScreen: AppScreens(route = "register")
    object LoginScreen: AppScreens(route = "login")

    object ClientDetailScreen: AppScreens(route = "detail_client")
    object NewClientScreen: AppScreens(route = "new_client")
}