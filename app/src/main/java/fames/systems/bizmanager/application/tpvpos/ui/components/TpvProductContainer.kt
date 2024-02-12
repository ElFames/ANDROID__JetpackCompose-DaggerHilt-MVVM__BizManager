package fames.systems.bizmanager.application.tpvpos.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fames.systems.bizmanager.R
import fames.systems.bizmanager.application.products.domain.models.Product

@Composable
fun TpvProductContainer(
    product: Pair<Int, Product>,
    onProductSelected: () -> Unit,
    onProductUnselected: () -> Unit,
    checkProductIsSelected: (Product) -> Boolean
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                if (checkProductIsSelected(product.second)) {
                    onProductUnselected()
                } else {
                    onProductSelected()
                }
            },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        border = BorderStroke(width = 2.dp, color = Color.Gray),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.auth_header_image),
                contentDescription = "imagen de prueba"
            )
            Row {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.second.name,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center,
                    style = TextStyle.Default
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.first.toString(),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center,
                    style = TextStyle.Default
                )
            }
        }

    }
}