package fames.systems.bizmanager.application.products.ui.myproducts.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.infrastructure.navigation.routes.AppScreens

@Composable
fun ProductCard(product: Product, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable { navController.navigate(route = AppScreens.ProductDetailScreen.route + "/${product.id}") },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(width = 2.dp, color = Color.Gray),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProductCardContent(product = product)
        }
    }
}
