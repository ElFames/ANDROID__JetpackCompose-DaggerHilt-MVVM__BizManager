package fames.systems.bizmanager.application.auth.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.login.components.ShowLoginErrorDialog
import fames.systems.bizmanager.application.auth.ui.login.components.ForgotPasswordButton
import fames.systems.bizmanager.application.auth.ui.login.components.LoginButton
import fames.systems.bizmanager.application.auth.ui.login.components.RememberRegisterLabel
import fames.systems.bizmanager.application.auth.ui.login.components.EmailTextField
import fames.systems.bizmanager.application.auth.ui.shared.HeaderImage
import fames.systems.bizmanager.application.auth.ui.shared.HorizontalLine
import fames.systems.bizmanager.application.auth.ui.login.components.PasswordTextField
import fames.systems.bizmanager.application.auth.ui.shared.ShowLoadingScreen
import fames.systems.bizmanager.resources.authBackgroundColor
import fames.systems.bizmanager.resources.buttonColor

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavHostController, initApp: () -> Unit) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val loginError: Boolean by viewModel.isLoginError.observeAsState(initial = false)
    val isValidUser: Boolean by viewModel.isValidate.observeAsState(initial = false)

    if (isLoading) ShowLoadingScreen()
    else {
        when {
            isValidUser -> {
                initApp()
            }
            loginError -> {
                ShowLoginErrorDialog(
                    onConfirmation = { viewModel.hideLoginError() },
                    icon = Icons.Default.Warning
                )
            }
            else -> {
                ShowLoginScreen(
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}

@Composable
private fun ShowLoginScreen(viewModel: LoginViewModel, navController: NavHostController) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)

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
            EmailTextField(email) { viewModel.onLoginChange(it, password) }
            PasswordTextField(password) { viewModel.onLoginChange(email, it) }
            RememberRegisterLabel(navController)
        }
        item {
            LoginButton(loginEnable) { viewModel.onLoginSelected(email, password) }
        }
        item {
            HorizontalLine(buttonColor)
            ForgotPasswordButton()
        }
    }
}