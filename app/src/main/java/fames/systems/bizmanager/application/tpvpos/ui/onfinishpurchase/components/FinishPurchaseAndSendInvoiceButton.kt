package fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.PurchaseInvoicerViewModel
import fames.systems.bizmanager.infrastructure.resources.disabledButtonColor

@Composable
fun FinishPurchaseAndSendInvoiceButton(
    viewModel: PurchaseInvoicerViewModel,
    isClientSelected: Boolean
) {
    Button(onClick = { viewModel.onFinishPurchaseAndSendInvoice() },
        enabled = isClientSelected,
        shape = ShapeDefaults.Small,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 25.dp, vertical = 7.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White,
            disabledContainerColor = disabledButtonColor,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Finalizar y enviar Factura")
    }
}