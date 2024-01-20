package fames.systems.bizmanager.application.clients.ui.components.clients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fames.systems.bizmanager.domain.models.Purchase

@Composable
fun PurchaseInfoContainer(purchase: Purchase, format: (Int) -> String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "${purchase.dateTime.day}/${format(purchase.dateTime.month)}/${purchase.dateTime.year} - ${format(purchase.dateTime.hour)}:${format(purchase.dateTime.minute)}",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
        purchase.products.forEach { product ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "${product.first}  ${product.second.name}",
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Total: ${"%.2f".format(purchase.totalPrice).replace('.',',')}",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }
    }
}