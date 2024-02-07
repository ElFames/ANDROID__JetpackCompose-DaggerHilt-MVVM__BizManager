package fames.systems.bizmanager.application.clients.ui.newclient

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.clients.ui.clientdetail.components.CancelButton
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientEmailTextField
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientNameTextField
import fames.systems.bizmanager.application.clients.ui.newclient.components.LoadingClientInsert
import fames.systems.bizmanager.application.clients.ui.newclient.components.NewClientTitle
import fames.systems.bizmanager.application.clients.ui.newclient.components.SaveButton
import fames.systems.bizmanager.application.clients.ui.newclient.components.ShowInsertError
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientAddressTextField
import fames.systems.bizmanager.application.clients.ui.newclient.components.textfields.ClientPhoneTextField
import fames.systems.bizmanager.domain.models.UiState

@Composable
fun NewClientScreen(viewModel: NewClientViewModel, navController: NavHostController) {
    val uiState: UiState by viewModel.uiState.collectAsState()

    when(uiState) {
        UiState.IDLE -> ShowNewClientScreen(viewModel, navController)
        UiState.LOADING -> LoadingClientInsert()
        UiState.ERROR -> ShowInsertError(navController) { viewModel.finishInsert() }
        UiState.SUCCESS -> {
            Toast.makeText(LocalContext.current, "Cliente añadido con éxito", Toast.LENGTH_SHORT).show()
            viewModel.finishInsert()
            navController.popBackStack()
        }
    }
}

@Composable
fun ShowNewClientScreen(viewModel: NewClientViewModel, navController: NavHostController) {
    val insertEnable by viewModel.insertEnable.observeAsState(initial = false)
    val name by viewModel.name.observeAsState(initial = "")
    val email by viewModel.email.observeAsState(initial = "")
    val phone by viewModel.phone.observeAsState(initial = "")
    val address by viewModel.address.observeAsState(initial = "")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { NewClientTitle() }
        item {
            ClientNameTextField(name) { viewModel.onInsertChange(it, email, phone, address) }
            ClientEmailTextField(email) { viewModel.onInsertChange(name, it, phone, address) }
            ClientPhoneTextField(phone) { viewModel.onInsertChange(name, email, it, address) }
            ClientAddressTextField(address) { viewModel.onInsertChange(name, email, phone, it) }
        }
        item {
            SaveButton(insertEnable) { viewModel.onSaveClient(name, email, phone, address) }
        }
    }
    CancelButton(navController, Color.Black)
}




