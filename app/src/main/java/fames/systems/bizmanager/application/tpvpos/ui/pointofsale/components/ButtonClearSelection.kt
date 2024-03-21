package fames.systems.bizmanager.application.tpvpos.ui.pointofsale.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.TpvPosViewModel

@Composable
fun ButtonClearSelection(viewModel: TpvPosViewModel, productsSelected: MutableList<Product>) {
    Button(
        onClick = { viewModel.clearAllSelections() },
        modifier = Modifier.padding(5.dp),
        enabled = productsSelected.size > 0,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.DarkGray
        )
    ) {
        Text(text = "Limpiar")
    }
}
