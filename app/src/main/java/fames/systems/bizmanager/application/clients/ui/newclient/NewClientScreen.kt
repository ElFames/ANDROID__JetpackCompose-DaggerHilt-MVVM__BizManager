package fames.systems.bizmanager.application.clients.ui.newclient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientEmailTextField
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientNameTextField
import fames.systems.bizmanager.application.clients.ui.newclient.components.SaveButton
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientAddressTextField
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientPhoneTextField
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.utils.dialogs.BasicAlertDialog
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.ShowLoadingView
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.TitleWithBackButton

@Composable
fun NewClientScreen(viewModel: NewClientViewModel, navController: NavHostController) {
    val uiState: UiState by viewModel.uiState.collectAsState()

    when(uiState) {
        UiState.IDLE -> NewClientScreenContent(viewModel, navController)
        UiState.LOADING -> ShowLoadingView()
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se ha podido añadir el cliente",
            color = Color.Red,
            onConfirmation = {
                viewModel.finishInsert()
                navController.popBackStack()
            }
        )
        UiState.SUCCESS -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Éxito",
            body = "Cliente añadido con éxito",
            color = Color.Green,
            onConfirmation = {
                viewModel.finishInsert()
                navController.popBackStack()
            }
        )

        UiState.PLUS -> TODO()
    }
}

@Composable
fun NewClientScreenContent(viewModel: NewClientViewModel, navController: NavHostController) {
    val insertEnable by viewModel.insertEnable.observeAsState(initial = false)
    val name by viewModel.name.observeAsState(initial = "")
    val email by viewModel.email.observeAsState(initial = "")
    val phone by viewModel.phone.observeAsState(initial = "")
    val address by viewModel.address.observeAsState(initial = "")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TitleWithBackButton("Nuevo Cliente", navController)
        }
        item {
            ClientNameTextField(name) { viewModel.onInsertChange(it, email, phone, address) }
            ClientEmailTextField(email) { viewModel.onInsertChange(name, it, phone, address) }
            ClientPhoneTextField(phone) { viewModel.onInsertChange(name, email, it, address) }
            ClientAddressTextField(address) { viewModel.onInsertChange(name, email, phone, it) }
        }
        item {
            SaveButton(insertEnable) { viewModel.onSaveClient(name, email, phone, address) }
        }
        item {  }
    }
}




