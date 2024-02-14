package fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.infrastructure.utils.RegularText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyReceivedTextField(received: Double, setReceived: (Double) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(200.dp)
                .padding(30.dp, 5.dp),
            value = received.toString(),
            onValueChange = { setReceived(it.toDouble()) },
            singleLine = true,
            label = { RegularText("Entregado") },
            shape = MaterialTheme.shapes.small,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.textFieldColors(
                errorLabelColor = Color.Red,
                errorIndicatorColor = Color.Red,
                textColor = Color.Black,
                containerColor = Color.White
            ),
        )
    }

}