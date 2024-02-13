package fames.systems.bizmanager.application.auth.ui.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.login.components.EmailTextField
import fames.systems.bizmanager.application.auth.ui.login.components.PasswordTextField
import fames.systems.bizmanager.application.auth.ui.register.components.AlertDialogError
import fames.systems.bizmanager.application.auth.ui.register.components.BottomInfoText
import fames.systems.bizmanager.application.auth.ui.register.components.ConfirmPasswordTextField
import fames.systems.bizmanager.application.auth.ui.register.components.RegisterButton
import fames.systems.bizmanager.application.auth.ui.register.components.RememberLoginLabel
import fames.systems.bizmanager.infrastructure.utils.HeaderImage
import fames.systems.bizmanager.infrastructure.utils.HorizontalLine
import fames.systems.bizmanager.application.auth.ui.register.components.UsernameTextField
import fames.systems.bizmanager.infrastructure.utils.ShowLoadingScreen
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.resources.authBackgroundColor
import fames.systems.bizmanager.infrastructure.resources.buttonColor


@Composable
fun RegisterScreen(viewModel: RegisterViewModel, navController: NavHostController) {
    val uiState: UiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> ShowRegisterScreen(viewModel, navController)
        UiState.LOADING -> ShowLoadingScreen()
        UiState.ERROR -> {
            AlertDialogError(
                icon = Icons.Default.Info,
                title = "Error al registrar",
                body = "El email está en uso",
                onConfirmation = { viewModel.finishRegister() },
            )
        }
        UiState.SUCCESS -> {
            Toast.makeText(LocalContext.current, "Registrado con éxito", Toast.LENGTH_SHORT).show()
            viewModel.finishRegister()
            navController.popBackStack()
        }
    }

}

@Composable
fun ShowRegisterScreen(viewModel: RegisterViewModel, navController: NavHostController) {
    val name: String by viewModel.name.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val confirmPassword: String by viewModel.confirmPassword.observeAsState(initial = "")
    val registerEnable: Boolean by viewModel.registerEnable.observeAsState(initial = false)

    LazyColumn(
        modifier = Modifier
            .background(color = authBackgroundColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            HeaderImage()
        }
        item {
            UsernameTextField(name) {
                viewModel.onRegisterChange(name = it, email, password, confirmPassword)
            }
            EmailTextField(email) {
                viewModel.onRegisterChange(name, email = it, password, confirmPassword)
            }
            PasswordTextField(password) {
                viewModel.onRegisterChange(name, email, password = it, confirmPassword)
            }
            ConfirmPasswordTextField(confirmPassword) {
                viewModel.onRegisterChange(name, email, password, confirmPassword = it)
            }
            RememberLoginLabel(navController)
        }
        item {
            RegisterButton(registerEnable) {
                viewModel.onRegisterSelected(
                    name,
                    email,
                    password
                )
            }
        }
        item {
            HorizontalLine(buttonColor)
            BottomInfoText()
        }
    }

}