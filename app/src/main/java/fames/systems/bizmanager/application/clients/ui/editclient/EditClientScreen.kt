package fames.systems.bizmanager.application.clients.ui.editclient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.clients.ui.newclient.components.SaveButton
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientAddressTextField
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientEmailTextField
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientNameTextField
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientPhoneTextField
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.utils.dialogs.BasicAlertDialog
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.ShowLoadingView
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.TitleWithBackButton

@Composable
fun EditClientScreen(
    viewModel: EditClientViewModel,
    navController: NavHostController,
    clientId: String
) {
    val uiState by viewModel.uiState.collectAsState(UiState.IDLE)

    when(uiState) {
        UiState.IDLE -> viewModel.getClient(clientId)
        UiState.LOADING -> ShowLoadingView()
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No hay conexión con el servidor, intente más tarde.",
            color = Color.Red,
            onConfirmation = {
                viewModel.finishEdit()
                navController.popBackStack()
            }
        )
        UiState.SUCCESS -> EditClientScreenContent(viewModel, navController)
        UiState.PLUS -> TODO()
    }
}

@Composable
fun EditClientScreenContent(viewModel: EditClientViewModel, navController: NavHostController) {
    val insertEnable by viewModel.insertEnable.observeAsState(initial = false)
    val insertSuccess by viewModel.insertSuccess.observeAsState(initial = false)
    val name by viewModel.name.observeAsState(initial = "")
    val email by viewModel.email.observeAsState(initial = "")
    val phone by viewModel.phone.observeAsState(initial = "")
    val address by viewModel.address.observeAsState(initial = "")

    if (insertSuccess) {
        BasicAlertDialog(
            icon = Icons.Default.CheckCircle,
            title = "Cliente Actualizado",
            body = "La información del cliente ha sido actualizada correctamente.",
            color = Color.Red,
            onConfirmation = {
                viewModel.finishEdit()
                navController.popBackStack()
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TitleWithBackButton("Editar Información", navController)
        }
        item {
            ClientNameTextField(name) { viewModel.onInsertChange(it, email, phone, address) }
            ClientEmailTextField(email) { viewModel.onInsertChange(name, it, phone, address) }
            ClientPhoneTextField(phone) { viewModel.onInsertChange(name, email, it, address) }
            ClientAddressTextField(address) { viewModel.onInsertChange(name, email, phone, it) }
        }
        item {
            SaveButton(insertEnable) { viewModel.updateClient(name, email, phone, address) }
        }
        item {  }
    }
}
