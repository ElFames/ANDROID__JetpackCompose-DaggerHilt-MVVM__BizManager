package fames.systems.bizmanager.application.dashboard.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.application.dashboard.ui.DashboardViewModel

@Composable
fun ShowStatistics(viewModel: DashboardViewModel) {
    val bestSellers by viewModel.bestSellers.observeAsState(initial = mutableListOf())
    val moreProfit by viewModel.moreProfit.observeAsState(initial = mutableListOf())
    val income by viewModel.income.observeAsState(initial = "")
    val expenses by viewModel.expenses.observeAsState(initial = "")
    val profit by viewModel.profit.observeAsState(initial = "")
    val numSales by viewModel.numOfSales.observeAsState(initial = "")

    LazyVerticalGrid(
        columns = GridCells.Adaptive(170.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        item {
            DashboardCard(
                title = "Ingresos",
                content = listOf(income),
                backgroundColor = Color.White
            )
        }
        item {
            DashboardCard(
                title = "Gastos",
                content = listOf(expenses),
                backgroundColor = Color.White
            )
        }
        item {
            DashboardCard(
                title = "Beneficios",
                content = listOf(profit),
                backgroundColor = Color.White
            )
        }
        item {
            DashboardCard(
                title = "Ventas",
                content = listOf(numSales),
                backgroundColor = Color.White
            )
        }
        item {
            val productsBestSeller = bestSellers.map { it.name }
            DashboardCard(
                title = "Más Vendidos",
                content = productsBestSeller,
                backgroundColor = Color.White
            )
        }
        item {
            val productsMoreProfit = moreProfit.map { it.name }
            DashboardCard(
                title = "Más Rentables",
                content = productsMoreProfit,
                backgroundColor = Color.White
            )
        }

    }
}