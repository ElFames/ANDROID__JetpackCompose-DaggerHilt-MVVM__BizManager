package fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.infrastructure.utils.values.Formats
import fames.systems.bizmanager.infrastructure.utils.values.RegularText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TotalPriceTextField(totalPrice: Double) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(200.dp)
                .padding(30.dp, 8.dp),
            value = Formats.price(totalPrice),
            onValueChange = { },
            singleLine = true,
            readOnly = true,
            label = { RegularText("Total") },
            shape = MaterialTheme.shapes.small,
            colors = TextFieldDefaults.textFieldColors(
                errorLabelColor = Color.Red,
                errorIndicatorColor = Color.Red,
                textColor = Color.Black,
                containerColor = Color.White
            ),
        )
    }

}