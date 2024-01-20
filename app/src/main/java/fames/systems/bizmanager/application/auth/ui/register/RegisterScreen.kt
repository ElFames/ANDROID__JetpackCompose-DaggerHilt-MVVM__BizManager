package fames.systems.bizmanager.application.auth.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.login.components.EmailTextField
import fames.systems.bizmanager.application.auth.ui.login.components.PasswordTextField
import fames.systems.bizmanager.application.auth.ui.login.components.RememberRegisterLabel
import fames.systems.bizmanager.application.auth.ui.register.components.AlertDialogRegisterError
import fames.systems.bizmanager.application.auth.ui.register.components.BottomInfoText
import fames.systems.bizmanager.application.auth.ui.register.components.ConfirmPasswordTextField
import fames.systems.bizmanager.application.auth.ui.register.components.RegisterButton
import fames.systems.bizmanager.application.auth.ui.register.components.RememberLoginLabel
import fames.systems.bizmanager.application.auth.ui.shared.HeaderImage
import fames.systems.bizmanager.application.auth.ui.shared.HorizontalLine
import fames.systems.bizmanager.application.auth.ui.register.components.UsernameTextField
import fames.systems.bizmanager.infrastructure.resources.authBackgroundColor
import fames.systems.bizmanager.infrastructure.resources.buttonColor


@Composable
fun RegisterScreen(viewModel: RegisterViewModel, navController: NavHostController) {
    CreateRegisterView(viewModel, navController)
}

@Composable
fun CreateRegisterView(viewModel: RegisterViewModel, navController: NavHostController) {
    val name: String by viewModel.name.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val confirmPassword: String by viewModel.confirmPassword.observeAsState(initial = "")
    val registerEnable: Boolean by viewModel.registerEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val registerError: Boolean by viewModel.isRegisterError.observeAsState(initial = false)

    if (isLoading) {
        Box(
            modifier = Modifier
                .background(color = authBackgroundColor)
                .fillMaxSize()
                .padding(0.dp, 75.dp)
        ) {
            HeaderImage()
            // progressbar o animacion
        }
    } else {
        when {
            registerError -> {
                AlertDialogRegisterError(
                    onConfirmation = { viewModel.hideRegisterError() },
                    icon = Icons.Default.Info
                )
            }
        }
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
                RegisterButton(registerEnable) { viewModel.onRegisterSelected(name, email, password) }
            }
            item {
                HorizontalLine(buttonColor)
                BottomInfoText()
            }
        }
    }
}



