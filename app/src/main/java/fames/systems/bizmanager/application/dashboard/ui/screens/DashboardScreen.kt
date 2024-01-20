package fames.systems.bizmanager.application.dashboard.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.shared.HorizontalLine
import fames.systems.bizmanager.application.dashboard.ui.viewmodel.DashboardViewModel
import fames.systems.bizmanager.application.dashboard.ui.components.FilterDashboardBar
import fames.systems.bizmanager.application.dashboard.ui.components.MyBarChart
import fames.systems.bizmanager.application.dashboard.ui.components.ShowStatistics
import fames.systems.bizmanager.infrastructure.resources.buttonColor

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {
    var showCharts by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(bottom = 70.dp)
    ) {
        InsertTitle("Panel de Estadísticas")
        HorizontalLine(color = Color.LightGray)
        FilterDashboardBar(viewModel, showCharts) { showCharts = it }
        if (showCharts) MyBarChart()
        else ShowStatistics(viewModel)
    }
}

@Composable
fun InsertTitle(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray), horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic
            ),
            color = buttonColor,
            modifier = Modifier.padding(16.dp)
        )
    }
}