package fames.systems.bizmanager.application.tpvpos.ui.pointofsale.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.infrastructure.utils.values.BoldText
import fames.systems.bizmanager.infrastructure.utils.values.Formats

@Composable
fun TotalPrice(totalPrice: Double?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BoldText("Total")
        val total = Formats.price(totalPrice ?: 0.0)
        BoldText(total + "€")
    }
}
