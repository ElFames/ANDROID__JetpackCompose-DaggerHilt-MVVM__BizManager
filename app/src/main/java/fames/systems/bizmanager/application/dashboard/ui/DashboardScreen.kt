package fames.systems.bizmanager.application.dashboard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import fames.systems.bizmanager.R
import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import fames.systems.bizmanager.application.dashboard.ui.components.FilterDashboardBar
import fames.systems.bizmanager.application.dashboard.ui.components.Statistics
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.navigation.routes.AppScreens
import fames.systems.bizmanager.infrastructure.utils.dialogs.BasicAlertDialog
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.InsertTitle
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.ShowLoadingView
import fames.systems.bizmanager.infrastructure.utils.values.BoldText
import fames.systems.bizmanager.infrastructure.utils.values.Formats
import fames.systems.bizmanager.infrastructure.utils.values.RegularText

@Composable
fun DashboardScreen(viewModel: DashboardViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> viewModel.updateFilterStatistics(Filter.DIA)
        UiState.LOADING -> {
            DashboardScreenContent(viewModel = viewModel, navController)
            ShowLoadingView()
        }

        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se pueden cargar las estadísticas en estos momentos.",
            color = Color.Red,
            onConfirmation = { }
        )

        UiState.SUCCESS -> DashboardScreenContent(viewModel = viewModel, navController)
        UiState.PLUS -> TODO()
    }
}

@Composable
fun DashboardScreenContent(viewModel: DashboardViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(bottom = 70.dp)
            .scrollable(ScrollableState { 0f }, Orientation.Vertical, true)
    ) {
        InsertTitle(title = "Estadísticas")
        FilterDashboardBar(viewModel)
        Statistics(viewModel)
        ProductsRanking(viewModel, navController)
    }
}

@Composable
fun ProductsRanking(viewModel: DashboardViewModel, navController: NavHostController) {
    val bestSellers by viewModel.bestSellers.observeAsState(initial = mutableListOf())
    val moreProfit by viewModel.moreProfit.observeAsState(initial = mutableListOf())

    Text(
        modifier = Modifier.padding(start = 15.dp),
        text = "Productos más vendidos",
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
    )
    ProductsList(bestSellers, navController)

    Text(
        modifier = Modifier.padding(start = 15.dp),
        text = "Productos más rentables",
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
    )

    ProductsList(moreProfit, navController)

}

@Composable
fun ProductsList(products: MutableList<Product>, navController: NavHostController) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        products.forEach {
            item {
                ProductItem(product = it, navController)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable { navController.navigate(route = AppScreens.ProductDetailScreen.route + "/${product.id}") },
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(width = 1.dp, color = Color.Gray),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val painter = rememberImagePainter(data = product.imageUrl) {
                crossfade(true)
                error(R.drawable.auth_header_image)
            }
            Image(
                painter = painter,
                contentDescription = "Product Image",
                modifier = Modifier.size(155.dp),
                contentScale = ContentScale.Crop
            )
            BoldText(text = product.name)
            RegularText(text = "Precio de venta: ${Formats.price(product.sellPrice)}€")

        }
    }

}
