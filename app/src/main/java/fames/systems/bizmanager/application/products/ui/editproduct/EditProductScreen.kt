package fames.systems.bizmanager.application.products.ui.editproduct

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.utils.dialogs.BasicAlertDialog
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.ShowLoadingView
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.TitleWithBackButton

@Composable
fun EditProductScreen(
    viewModel: EditProductViewModel,
    navController: NavHostController,
    productId: String
) {
    val uiState by viewModel.uiState.collectAsState()

    when(uiState) {
        UiState.IDLE -> viewModel.getProduct(productId)
        UiState.LOADING -> {
            EditProductScreenContent(viewModel, navController)
            ShowLoadingView()
        }
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se ha podido cargar el producto",
            color = Color.Red,
            onConfirmation = { navController.popBackStack() }
        )
        UiState.SUCCESS -> EditProductScreenContent(viewModel, navController)
        UiState.PLUS -> TODO()
    }
}

@Composable
fun EditProductScreenContent(viewModel: EditProductViewModel, navController: NavHostController) {
    TitleWithBackButton(title = "Editar Información", navController = navController)
}
