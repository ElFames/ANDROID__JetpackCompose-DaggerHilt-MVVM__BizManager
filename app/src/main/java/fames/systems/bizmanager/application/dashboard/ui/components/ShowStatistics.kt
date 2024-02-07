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
import fames.systems.bizmanager.application.dashboard.ui.viewmodel.DashboardViewModel

@Composable
fun ShowStatistics(viewModel: DashboardViewModel) {
    val bestSellers: List<String> by viewModel.bestSellers.observeAsState(initial = listOf())
    val moreProfit: List<String> by viewModel.moreProfit.observeAsState(initial = listOf())
    val income: List<String> by viewModel.income.observeAsState(initial = listOf())
    val expenses: List<String> by viewModel.expenses.observeAsState(initial = listOf())
    val profit: List<String> by viewModel.profit.observeAsState(initial = listOf())
    val numSales: List<String> by viewModel.numOfSales.observeAsState(initial = listOf())

    LazyVerticalGrid(
        columns = GridCells.Adaptive(170.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        item {
            DashboardCard(
                title = "Ingresos",
                content = income,
                backgroundColor = Color.White
            )
        }
        item {
            DashboardCard(
                title = "Gastos",
                content = expenses,
                backgroundColor = Color.White
            )
        }
        item {
            DashboardCard(
                title = "Beneficios",
                content = profit,
                backgroundColor = Color.White
            )
        }
        item {
            DashboardCard(
                title = "Ventas",
                content = numSales,
                backgroundColor = Color.White
            )
        }
        item {
            DashboardCard(
                title = "Más Vendidos",
                content = bestSellers,
                backgroundColor = Color.White
            )
        }
        item {
            DashboardCard(
                title = "Más Rentables",
                content = moreProfit,
                backgroundColor = Color.White
            )
        }


    }
}