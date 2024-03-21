package fames.systems.bizmanager.application.tpvpos.ui.pointofsale.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.TpvPosViewModel
import fames.systems.bizmanager.infrastructure.resources.buttonColor

@Composable
fun ButtonOnFinishSelection(viewModel: TpvPosViewModel, isSellEnable: Boolean) {
    Button(
        onClick = {
            viewModel.onFinishSelection()
        },
        modifier = Modifier.padding(5.dp).width(Dp.Infinity),
        enabled = isSellEnable,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.DarkGray
        )
    ) {
        Text(text = "Finalizar Compra")
    }
}
