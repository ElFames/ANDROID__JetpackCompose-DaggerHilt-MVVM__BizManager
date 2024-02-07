package fames.systems.bizmanager.application.tpvpos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.dashboard.ui.screens.InsertTitle

@Composable
fun TpvPosScreen(viewModel: TpvPosViewModel, navController: NavHostController) {
    val allProducts by viewModel.allProducts.observeAsState(initial = mutableListOf())
    val allClients by viewModel.allClients.observeAsState(initial = mutableListOf())
    val productsSelected by viewModel.productsSelected.observeAsState(initial = mutableListOf())
    val clientSelected by viewModel.clientSelected.observeAsState()
    
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier.fillMaxSize(),
        shape = RectangleShape
    ) {
        InsertTitle("Punto de Venta")
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxSize()
                    .weight(0.5f)
            ) {
                item {
                    Text(text = "izquierda")
                }
            }

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