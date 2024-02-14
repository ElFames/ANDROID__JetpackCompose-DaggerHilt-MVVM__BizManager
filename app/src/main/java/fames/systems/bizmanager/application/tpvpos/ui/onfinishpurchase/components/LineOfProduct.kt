package fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.infrastructure.utils.Formats
import fames.systems.bizmanager.infrastructure.utils.RegularText

@Composable
fun LineOfProduct(it: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RegularText(text = it.unds.toString())
        RegularText(text = it.name)
        RegularText(text = Formats.price(it.sellPrice * it.unds))
    }
}