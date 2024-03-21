package fames.systems.bizmanager.application.products.ui.myproducts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.products.ui.myproducts.components.ProductCard
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.navigation.routes.AppScreens
import fames.systems.bizmanager.infrastructure.utils.dialogs.BasicAlertDialog
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.HorizontalLine
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.InsertTitle
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.ShowLoadingView

@Composable
fun MyProductsScreen(viewModel: MyProductsViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> viewModel.loadProducts()
        UiState.LOADING -> {
            ShowProductsScreen(viewModel = viewModel, navController = navController)
            ShowLoadingView()
        }
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No hay conexión con el servidor",
            color = Color.Red,
            onConfirmation = { navController.popBackStack() }
        )
        UiState.SUCCESS -> ShowProductsScreen(viewModel = viewModel, navController)
        UiState.PLUS -> TODO()
    }
}

@Composable
fun ShowProductsScreen(viewModel: MyProductsViewModel, navController: NavHostController) {
    val products by viewModel.products.observeAsState(initial = mutableListOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(bottom = 70.dp)
    ) {
        InsertTitle("Productos")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AddProductButton(navController)
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(230.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            products.forEach { product ->
                item {
                    ProductCard(product = product, navController = navController)
                }
            }
        }
    }
}

@Composable
fun AddProductButton(navController: NavHostController) {
    Card(
        modifier = Modifier.padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        shape = CircleShape
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(15.dp)
                .size(35.dp)
                .clickable { navController.navigate(AppScreens.NewProductScreen.route) },
            imageVector = Icons.Default.Add,
            contentDescription = "info icon"
        )
    }
}

