package fames.systems.bizmanager.application.dashboard.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import fames.systems.bizmanager.application.dashboard.ui.DashboardViewModel
import fames.systems.bizmanager.resources.buttonColor
import fames.systems.bizmanager.resources.card
import fames.systems.bizmanager.resources.orange

@Composable
fun ShowStatistics(viewModel: DashboardViewModel) {
    val bestSellers: List<String> by viewModel.bestSellers.observeAsState(initial = listOf())
    val moreProfit: List<String> by viewModel.moreProfit.observeAsState(initial = listOf())
    val income: List<String> by viewModel.income.observeAsState(initial = listOf())
    val expenses: List<String> by viewModel.expenses.observeAsState(initial = listOf())
    val profit: List<String> by viewModel.profit.observeAsState(initial = listOf())
    val numSales: List<String> by viewModel.numSales.observeAsState(initial = listOf())

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        item {
            DashboardCard(
                title = "Ingresos",
                content = income,
                backgroundColor = orange
            )
        }
        item {
            DashboardCard(
                title = "Gastos",
                content = expenses,
                backgroundColor = orange
            )
        }
        item {
            DashboardCard(
                title = "Beneficios",
                content = profit,
                backgroundColor = orange
            )
        }
        item {
            DashboardCard(
                title = "Ventas",
                content = numSales,
                backgroundColor = card
            )
        }
        item {
            DashboardCard(
                title = "Más Vendidos",
                content = bestSellers,
                backgroundColor = card
            )
        }
        item {
            DashboardCard(
                title = "Más Rentables",
                content = moreProfit,
                backgroundColor = card
            )
        }


    }
}