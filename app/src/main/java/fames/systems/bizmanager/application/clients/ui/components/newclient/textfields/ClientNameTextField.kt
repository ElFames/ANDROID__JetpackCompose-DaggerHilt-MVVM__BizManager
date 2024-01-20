package fames.systems.bizmanager.application.clients.ui.components.newclient.textfields

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.infrastructure.resources.authBackgroundColor
import fames.systems.bizmanager.infrastructure.resources.buttonColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientNameTextField(name: String, onInsertChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = { onInsertChange(it) },
        placeholder = { Text(text = "Introduce el nombre...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .border(border = BorderStroke(1.dp, buttonColor), shape = ShapeDefaults.Small)
            .fillMaxWidth()
            .height(52.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Person, contentDescription = "name_icon", tint = Color.Black)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            containerColor = Color.White
        ),
    )
}