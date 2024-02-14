package fames.systems.bizmanager.application.tpvpos.ui.pointofsale

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.register.components.AlertDialogError
import fames.systems.bizmanager.infrastructure.utils.ShowLoadingScreen
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.components.ButtonClearSelection
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.components.ButtonOnFinishSelection
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.components.ClientSelectedInfo
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.components.DiscountTextField
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.components.TotalPrice
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.components.TpvProductContainer
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.navigation.routes.AppScreens
import fames.systems.bizmanager.infrastructure.navigation.routes.BottomBarScreens
import fames.systems.bizmanager.infrastructure.utils.Formats
import fames.systems.bizmanager.infrastructure.utils.InsertTitle
import fames.systems.bizmanager.infrastructure.utils.RegularText

@Composable
fun TpvPosScreen(viewModel: TpvPosViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> ShowTpvPos(viewModel = viewModel, navController = navController)
        UiState.LOADING -> {
            ShowTpvPos(viewModel = viewModel, navController = navController)
            ShowLoadingScreen()
        }
        UiState.ERROR -> AlertDialogError(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No hay conexión con el servidor",
            onConfirmation = { viewModel.finishSelection() }
        )

        UiState.SUCCESS -> {
            viewModel.finishSelection()
            navController.navigate(AppScreens.PurchaseInvoicerScreen.route)
        }
    }

}

@Composable
fun ShowTpvPos(viewModel: TpvPosViewModel, navController: NavHostController) {
    val allProducts by viewModel.allProducts.observeAsState(initial = mutableListOf())
    val productsSelected by viewModel.productsSelected.observeAsState(initial = mutableListOf())
    val clientSelected by viewModel.clientSelected.observeAsState()
    val totalPrice by viewModel.totalPrice.observeAsState()
    val isSellEnable by viewModel.isSellEnable.observeAsState(initial = true)

    if (!isSellEnable) {
        AlertDialogError(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "Escoge almenos un producto",
            onConfirmation = { viewModel.sellEnable() }
        )
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp),
        shape = RectangleShape
    ) {
        InsertTitle("Punto de Venta")

        ClientSelectedInfo(clientSelected, viewModel) {
            navController.navigate(BottomBarScreens.ClientsScreen.route)
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {

            // Products Selector
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .weight(0.5f)
            ) {
                if (allProducts.isEmpty()) viewModel.loadProducts()
                allProducts.forEach { product ->
                    val unds = viewModel.checkProductIsSelected(product)
                    item {
                        TpvProductContainer(
                            product = product,
                            onProductSelected = { viewModel.selectProduct(product) },
                            unds = unds
                        )
                    }
                }
            }

            var lastDiscount by rememberSaveable { mutableStateOf("0") }
            LaunchedEffect(totalPrice) {
                lastDiscount = "0"
            }

            // Purchase Summary
            LazyColumn(
                modifier = Modifier
                    .padding(5.dp, 15.dp)
                    .fillMaxSize()
                    .weight(0.5f)
                    .border(1.dp, Color.Gray, MaterialTheme.shapes.medium)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                productsSelected.forEach { product ->
                    val price = Formats.price(product.sellPrice * product.unds)
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            RegularText(product.unds.toString())
                            RegularText(product.name)
                            RegularText(price + "€")
                        }
                    }
                }

                item {}

                item {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                            .weight(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TotalPrice(totalPrice)
                        DiscountTextField(viewModel, lastDiscount)
                        ButtonOnFinishSelection(viewModel, isSellEnable)
                        ButtonClearSelection(viewModel, productsSelected)
                    }
                }
            }
        }
    }

}


