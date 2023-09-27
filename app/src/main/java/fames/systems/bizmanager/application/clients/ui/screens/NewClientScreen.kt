package fames.systems.bizmanager.application.clients.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.clients.ui.components.newclient.CancelButton
import fames.systems.bizmanager.application.clients.ui.components.newclient.textfields.ClientEmailTextField
import fames.systems.bizmanager.application.clients.ui.components.newclient.textfields.ClientNameTextField
import fames.systems.bizmanager.application.clients.ui.components.newclient.LoadingClientInsert
import fames.systems.bizmanager.application.clients.ui.components.newclient.NewClientTitle
import fames.systems.bizmanager.application.clients.ui.components.newclient.SaveButton
import fames.systems.bizmanager.application.clients.ui.components.newclient.ShowInsertError
import fames.systems.bizmanager.application.clients.ui.components.newclient.textfields.ClientAddressTextField
import fames.systems.bizmanager.application.clients.ui.components.newclient.textfields.ClientPhoneTextField
import fames.systems.bizmanager.application.clients.ui.viewmodel.NewClientViewModel

@Composable
fun NewClientScreen(viewModel: NewClientViewModel, navController: NavHostController) {
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val isInsertError by viewModel.isInsertError.observeAsState(initial = Pair(false, ""))
    val isInsertSuccessful by viewModel.insertSuccessful.observeAsState(initial = false)

    if (isLoading) {
        LoadingClientInsert()
    } else if (isInsertError.first) {
        ShowInsertError(navController) { viewModel.hideError() }
    } else if (isInsertSuccessful) {
        viewModel.finishInsert()
        Toast.makeText(LocalContext.current, "Cliente añadido con éxito", Toast.LENGTH_SHORT).show()
        navController.popBackStack()
    } else {
        ShowNewClientScreen(viewModel, navController)
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
    CancelButton(navController)
}




