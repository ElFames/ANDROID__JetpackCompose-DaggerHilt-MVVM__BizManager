package fames.systems.bizmanager.application.dashboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.application.dashboard.ui.DashboardViewModel

@Composable
fun Statistics(viewModel: DashboardViewModel) {
    val income by viewModel.income.observeAsState(initial = "")
    val expenses by viewModel.expenses.observeAsState(initial = "")
    val profit by viewModel.profit.observeAsState(initial = "")
    val numSales by viewModel.numOfSales.observeAsState(initial = "")

    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DashboardCard(
            title = "Ingresos",
            content = listOf(income),
            backgroundColor = Color.White
        )
        DashboardCard(
            title = "Gastos",
            content = listOf(expenses),
            backgroundColor = Color.White
        )
        DashboardCard(
            title = "Beneficios",
            content = listOf(profit),
            backgroundColor = Color.White
        )
        DashboardCard(
            title = "Ventas",
            content = listOf(numSales),
            backgroundColor = Color.White
        )

    }
}