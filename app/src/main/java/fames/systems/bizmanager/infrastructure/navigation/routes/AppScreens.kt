package fames.systems.bizmanager.infrastructure.navigation.routes


sealed class AppScreens(val route: String) {
    object AuthScreen: AppScreens(route = "auth")
    object RegisterScreen: AppScreens(route = "register")
    object LoginScreen: AppScreens(route = "login")

    object ClientDetailScreen: AppScreens(route = "detail_client")
    object NewClientScreen: AppScreens(route = "new_client")
    object EditClientScreen: AppScreens(route = "edit_client")

    object PurchaseInvoicerScreen: AppScreens(route = "purchase_invoice")

    object ProductDetailScreen: AppScreens(route = "detail_product")
    object NewProductScreen: AppScreens(route = "new_product")
    object EditProductScreen: AppScreens(route = "edit_product")
}