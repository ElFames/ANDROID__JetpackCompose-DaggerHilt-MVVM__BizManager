package fames.systems.bizmanager.application.auth.ui.login.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import fames.systems.bizmanager.R
import fames.systems.bizmanager.application.auth.ui.login.LoginViewModel
import fames.systems.bizmanager.infrastructure.resources.authBackgroundColor
import fames.systems.bizmanager.infrastructure.resources.buttonColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PasswordTextField(password: String, onLoginChange: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        visualTransformation = PasswordVisualTransformation(),
        value = password,
        onValueChange = { onLoginChange(it)  },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .border(border = BorderStroke(1.dp, buttonColor), shape = ShapeDefaults.Small)
            .fillMaxWidth()
            .height(52.dp),
        placeholder = { Text("Introduce tu contraseña...", color = Color.Gray) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "password_icon", tint = Color.White)
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.White,
            containerColor = authBackgroundColor
        ),
    )
}