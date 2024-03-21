package fames.systems.bizmanager.application.products.ui.myproducts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import fames.systems.bizmanager.R
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.infrastructure.utils.values.BoldText
import fames.systems.bizmanager.infrastructure.utils.values.Formats
import fames.systems.bizmanager.infrastructure.utils.values.RegularText


@Composable
fun ProductCardContent(product: Product) {
    if (product.imageUrl != null && product.imageUrl != "") {
        val painter = rememberImagePainter(data = product.imageUrl)
        Image(
            painter = painter,
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.auth_header_image),
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    BoldText(text = product.name)
    Spacer(modifier = Modifier.height(5.dp))
    RegularText(text = "${Formats.price(product.sellPrice)} €")
}