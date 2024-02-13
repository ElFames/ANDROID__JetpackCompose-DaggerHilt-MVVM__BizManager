package fames.systems.bizmanager.application.clients.ui.myclients.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.R
import fames.systems.bizmanager.infrastructure.resources.buttonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientSearcher(clientToSearch: String, onUserToSearchChange: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        modifier = Modifier
            .height(76.dp)
            .width(280.dp)
            .padding(12.dp),
        value = clientToSearch,
        onValueChange = { onUserToSearchChange(it) },
        placeholder = { Text(text = "Buscador...") },
        leadingIcon = { Icon(painter = painterResource(id = R.drawable.search_icon), contentDescription = "search_icon") },
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        singleLine = true,
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            containerColor = Color.LightGray,
            cursorColor = buttonColor
        )
    )
}
