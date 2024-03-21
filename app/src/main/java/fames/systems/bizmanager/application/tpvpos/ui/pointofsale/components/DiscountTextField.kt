package fames.systems.bizmanager.application.tpvpos.ui.pointofsale.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.TpvPosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscountTextField(viewModel: TpvPosViewModel, lastDiscount: String) {
    var discount by rememberSaveable { mutableStateOf(lastDiscount) }
    OutlinedTextField(
        modifier = Modifier.padding(7.dp, 15.dp).width(Dp.Infinity),
        value = discount,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        onValueChange = { newDiscount ->
            if (newDiscount.isEmpty() || newDiscount.isDigitsOnly()) {
                discount = newDiscount
                val discountValue = if (newDiscount.isEmpty()) 0 else newDiscount.toInt()
                viewModel.applyDiscount(discountValue)
            }
        },
        label = { Text(text = "Descuento %") }
    )
}

fun String.isDigitsOnly(): Boolean {
    return all { it.isDigit() }
}
