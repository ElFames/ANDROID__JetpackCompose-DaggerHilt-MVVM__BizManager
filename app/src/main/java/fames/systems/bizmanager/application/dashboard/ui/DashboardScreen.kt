package fames.systems.bizmanager.application.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.shared.HorizontalLine
import fames.systems.bizmanager.application.dashboard.ui.components.FilterBar
import fames.systems.bizmanager.application.dashboard.ui.components.MyBarChart
import fames.systems.bizmanager.application.dashboard.ui.components.ShowStatistics
import fames.systems.bizmanager.application.dashboard.ui.components.TopBar

@Composable
fun DashboardScreen(viewModel: DashboardViewModel, navController: NavHostController) {
    var navMenuIsOpen by rememberSaveable { mutableStateOf(false) }
    var showCharts by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray)
    ) {
        TopBar { navMenuIsOpen = !navMenuIsOpen }
        HorizontalLine(color = Color.LightGray)
        FilterBar(viewModel, showCharts) { showCharts = it }
        if (showCharts) MyBarChart()
        else ShowStatistics(viewModel)
    }
}