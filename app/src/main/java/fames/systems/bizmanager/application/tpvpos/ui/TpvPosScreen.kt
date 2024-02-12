package fames.systems.bizmanager.application.tpvpos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.register.components.AlertDialogError
import fames.systems.bizmanager.application.auth.ui.shared.ShowLoadingScreen
import fames.systems.bizmanager.application.dashboard.ui.screens.InsertTitle
import fames.systems.bizmanager.application.tpvpos.ui.components.ClientSelectedInfo
import fames.systems.bizmanager.application.tpvpos.ui.components.TpvProductContainer
import fames.systems.bizmanager.domain.models.UiState

@Composable
fun TpvPosScreen(viewModel: TpvPosViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> ShowTpvPos(viewModel = viewModel, navController = navController)
        UiState.LOADING -> ShowLoadingScreen()
        UiState.ERROR -> AlertDialogError(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No hay conexión con el servidor",
            onConfirmation = { viewModel.hideError() }
        )

        UiState.SUCCESS -> {}// venta realizada, navegar a pantalla de resumen de compra y factura
    }

}

@Composable
fun ShowTpvPos(viewModel: TpvPosViewModel, navController: NavHostController) {
    val allProducts by viewModel.allProducts.observeAsState(initial = mutableListOf())
    val allClients by viewModel.allClients.observeAsState(initial = mutableListOf())
    val productsSelected by viewModel.productsSelected.observeAsState(initial = mutableListOf())
    val clientSelected by viewModel.clientSelected.observeAsState()
    val isSellEnable by viewModel.isSellEnable.observeAsState(initial = true)

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp),
        shape = RectangleShape
    ) {
        InsertTitle("Punto de Venta")
        ClientSelectedInfo(clientSelected, viewModel)

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            // Products Selector
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxSize()
                    .weight(0.5f)
            ) {
                allProducts.forEach { prod ->
                    val product = productsSelected.find { it.second.id == prod.id } ?: Pair(0, prod)
                    item {
                        TpvProductContainer(
                            product = product,
                            onProductSelected = { viewModel.selectProduct(product.second) },
                            onProductUnselected = { viewModel.unselectProduct(product.second) },
                            checkProductIsSelected = { viewModel.checkProductIsSelected(product.second) }
                        )
                    }
                }
            }

            // Purchase Summary
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                item {
                    Text(text = "derecha")
                }
                item {
                    Button(onClick = {}) {
                        Text(text = "Comprar")
                    }
                }
            }

        }
    }

}
