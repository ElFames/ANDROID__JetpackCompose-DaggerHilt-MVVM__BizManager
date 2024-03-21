package fames.systems.bizmanager.application.auth.ui.login

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.login.components.ForgotPasswordButton
import fames.systems.bizmanager.application.auth.ui.login.components.LoginButton
import fames.systems.bizmanager.application.auth.ui.login.components.RememberRegisterLabel
import fames.systems.bizmanager.application.auth.ui.login.components.EmailTextField
import fames.systems.bizmanager.application.auth.ui.login.components.PasswordTextField
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.resources.authBackgroundColor
import fames.systems.bizmanager.infrastructure.resources.buttonColor
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.HeaderImage
import fames.systems.bizmanager.infrastructure.utils.dialogs.BasicAlertDialog
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.HorizontalLine
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.ShowLoadingView

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavHostController, initApp: () -> Unit) {
    val uiState: UiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var rememberLogin by remember { mutableStateOf(false) }

    rememberLogin = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
        .getBoolean("remember_login", false)
    if (rememberLogin) {
        val email = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
            .getString("email", "")
        val password = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
            .getString("password", "")
        viewModel.onLoginSelected(email!!, password!!)
    }

    when (uiState) {
        UiState.IDLE -> LoginScreenContent(
            viewModel,
            navController,
            rememberLogin
        ) { rememberLogin = it }

        UiState.LOADING -> {
            LoginScreenContent(viewModel, navController, rememberLogin) { rememberLogin = it }
            ShowLoadingView()
        }

        UiState.ERROR -> {
            BasicAlertDialog(
                icon = Icons.Default.Warning,
                title = "Error de autenticación",
                body = "Las credenciales no són correctas",
                color = Color.Red,
                onConfirmation = {
                    viewModel.finishLogin()
                    saveSharedPreferences("", "", false, context)
                },
            )
        }

        UiState.SUCCESS -> {
            viewModel.loadInitialData()
            saveSharedPreferences(
                viewModel.email.value,
                viewModel.password.value,
                rememberLogin,
                context
            )
            initApp()
        }

        UiState.PLUS -> TODO()
    }
}

@Composable
private fun LoginScreenContent(
    viewModel: LoginViewModel,
    navController: NavHostController,
    rememberLogin: Boolean,
    onRememberLoginChange: (Boolean) -> Unit
) {
    val email by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val loginEnable by viewModel.loginEnable.observeAsState(initial = false)

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
            RememberLoginSwitch(
                email,
                password,
                isChecked = rememberLogin,
                onCheckedChange = onRememberLoginChange
            )
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


@Composable
fun RememberLoginSwitch(
    email: String?,
    password: String?,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(text = "Recordar inicio de sesión", fontSize = 14.sp, color = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Switch(
            checked = isChecked,
            onCheckedChange = { checked ->
                onCheckedChange(checked)
                context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
                    .edit()
                    .putBoolean("remember_login", checked)
                    .putString("email", email)
                    .putString("password", password)
                    .apply()
            }
        )
    }
}

fun saveSharedPreferences(
    email: String?,
    password: String?,
    rememberLogin: Boolean,
    context: Context
) {
    if (rememberLogin) {
        context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("remember_login", true)
            .putString("email", email)
            .putString("password", password)
            .apply()
    } else {
        context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("remember_login", false)
            .putString("email", "")
            .putString("password", "")
            .apply()
    }
}