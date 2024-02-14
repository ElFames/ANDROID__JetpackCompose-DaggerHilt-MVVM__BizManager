package fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fames.systems.bizmanager.domain.models.Purchase

@Composable
fun DateTimeStamp(purchase: Purchase) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(12.dp, 5.dp, 12.dp, 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = purchase.dateTime.getStringDateTime(),
            color = Color.Black,
            fontFamily = FontFamily.Serif,
            fontSize = 15.sp
        )
    }
}