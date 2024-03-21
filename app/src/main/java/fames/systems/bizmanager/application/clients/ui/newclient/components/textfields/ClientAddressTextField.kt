package fames.systems.bizmanager.application.clients.ui.newclient.components.textfields

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.infrastructure.resources.buttonColor
import okhttp3.internal.wait


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientAddressTextField(address: String, onInsertChange: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = address,
        onValueChange = { onInsertChange(it) },
        placeholder = { Text(text = "Introduce la dirección...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .border(border = BorderStroke(1.dp, buttonColor), shape = ShapeDefaults.Small)
            .width(Dp.Infinity)
            .height(52.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Home, contentDescription = "address_icon", tint = Color.Black)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            containerColor = Color.White
        ),
    )
}