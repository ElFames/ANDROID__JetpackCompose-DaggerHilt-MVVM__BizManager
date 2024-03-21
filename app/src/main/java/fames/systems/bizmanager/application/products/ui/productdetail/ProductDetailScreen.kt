package fames.systems.bizmanager.application.products.ui.productdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import fames.systems.bizmanager.R
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.application.products.domain.models.SubProduct
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.navigation.routes.AppScreens
import fames.systems.bizmanager.infrastructure.resources.buttonColor
import fames.systems.bizmanager.infrastructure.utils.dialogs.BasicAlertDialog
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.ShowLoadingView
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.TitleWithBackButton
import fames.systems.bizmanager.infrastructure.utils.values.BoldText
import fames.systems.bizmanager.infrastructure.utils.values.Formats
import fames.systems.bizmanager.infrastructure.utils.values.RegularText

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel,
    navController: NavHostController,
    productId: String
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> viewModel.getProduct(productId)
        UiState.LOADING -> {
            ProductDetailScreenContent(viewModel, navController)
            ShowLoadingView()
        }

        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error al cargar producto",
            body = "No se ha podido cargar la información del producto",
            color = Color.Red,
            onConfirmation = { navController.popBackStack() }
        )

        UiState.SUCCESS -> ProductDetailScreenContent(viewModel, navController)
        UiState.PLUS -> TODO()
    }
}

@Composable
fun ProductDetailScreenContent(
    viewModel: ProductDetailViewModel,
    navController: NavHostController
) {
    val product by viewModel.product.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .scrollable(
                state = ScrollableState { 0f },
                orientation = Orientation.Vertical,
                enabled = true,
            ),
        verticalArrangement = Arrangement.Top
    ) {
        TitleWithBackButton(title = "Detalles del producto", navController)
        ProductTopToolbar(product, navController)
        ProductInfo(product)
        ProductStatistics(product)
    }
}

@Composable
fun ProductStatistics(product: Product?) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        ProductExpenses(product)
        Spacer(modifier = Modifier.height(12.dp))
        ProductMargins(product)
    }
}


@Composable
fun ProductTopToolbar(product: Product?, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(13.dp, 13.dp, 13.dp, bottom = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Image(
            modifier = Modifier
                .border(1.dp, Color.DarkGray, shape = MaterialTheme.shapes.medium)
                .padding(5.dp)
                .clickable { navController.navigate(AppScreens.EditProductScreen.route + "/${product?.id}") },
            imageVector = Icons.Default.Edit,
            contentDescription = "edit_icon"
        )
    }
}

@Composable
fun ProductInfo(product: Product?) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ProductImage(product?.imageUrl)
        product?.let { ProductInfoContainer(product) }
    }
}

@Composable
fun ProductExpenses(product: Product?) {
    BoldText(text = "Costes del producto")
    product?.expenses?.forEach {
        ProductExpenseLine(it)
    }

}

@Composable
fun ProductExpenseLine(it: SubProduct) {
    Row(
        Modifier
            .padding(12.dp, 5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RegularText(it.quantity.toString())
        RegularText(it.quantityCurrency)
        RegularText(" de ${it.name}")
        Spacer(modifier = Modifier.width(5.dp))
        RegularText("${Formats.price(it.costPrice)}€")
    }
}

@Composable
fun ProductMargins(product: Product?) {
    BoldText(text = "Margenes del producto")
    val totalExpenses = product?.expenses?.sumOf { it.costPrice } ?: 0.0
    RegularText(text = "Coste Total: ${Formats.price(totalExpenses)}€")
    val profit = ((product?.sellPrice ?: 0.0) - totalExpenses)
    RegularText(text = "Beneficio: ${Formats.price(profit)}€")
    val percentageProfit = (profit / totalExpenses) * 100
    RegularText(text = "Margen: ${Formats.price(percentageProfit)}%")

}

@Composable
fun ProductInfoContainer(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Text(
            modifier = Modifier.padding(start = 15.dp, top = 8.dp),
            text = "${Formats.price(product.sellPrice)}€",
            color = buttonColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 12.dp, 25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = product.name,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = product.description,
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Composable
fun ProductImage(imageUrl: String?) {
    if (imageUrl != null && imageUrl != "") {
        val painter = rememberImagePainter(data = imageUrl)
        Image(
            painter = painter,
            contentDescription = "Product Image",
            modifier = Modifier.size(220.dp),
            contentScale = ContentScale.Crop
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.auth_header_image),
            contentDescription = "Product Image",
            modifier = Modifier.size(220.dp),
            contentScale = ContentScale.Crop
        )
    }
}
