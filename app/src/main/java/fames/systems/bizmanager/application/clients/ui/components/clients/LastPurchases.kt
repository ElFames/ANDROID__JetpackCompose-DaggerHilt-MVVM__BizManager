package fames.systems.bizmanager.application.clients.ui.components.clients

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.domain.models.Purchase

@Composable
fun LastPurchases(client: Client?, format: (Int) -> String) {
    Text(
        modifier = Modifier.padding(start = 17.dp, top = 25.dp),
        text = "Últimos pedidos",
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        fontSize = 19.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Black
    )
    LazyVerticalGrid(
        columns = GridCells.Adaptive(220.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        client?.purchases?.sortedWith(
            compareBy<Purchase> { it.dateTime.year }
                .thenBy { it.dateTime.month }
                .thenBy { it.dateTime.day }
                .thenBy { it.dateTime.hour }
                .thenBy { it.dateTime.minute }
        )?.forEach { purchase ->
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = MaterialTheme.shapes.small,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    elevation = CardDefaults.cardElevation(4.dp),
                    border = BorderStroke(2.dp, color = Color.Gray)
                ) {
                    PurchaseInfoContainer(purchase, format)
                }
            }
        }
    }
}