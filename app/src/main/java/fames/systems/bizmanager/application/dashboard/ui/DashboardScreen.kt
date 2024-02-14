package fames.systems.bizmanager.application.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import fames.systems.bizmanager.application.auth.ui.register.components.AlertDialogError
import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import fames.systems.bizmanager.infrastructure.utils.HorizontalLine
import fames.systems.bizmanager.application.dashboard.ui.components.FilterDashboardBar
import fames.systems.bizmanager.application.dashboard.ui.components.MyBarChart
import fames.systems.bizmanager.application.dashboard.ui.components.ShowStatistics
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.ShowTpvPos
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.navigation.routes.AppScreens
import fames.systems.bizmanager.infrastructure.resources.buttonColor
import fames.systems.bizmanager.infrastructure.utils.InsertTitle
import fames.systems.bizmanager.infrastructure.utils.ShowLoadingScreen

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> viewModel.updateFilterStatistics(Filter.DIA)
        UiState.LOADING -> {
            ShowDashboardScreen(viewModel = viewModel)
            ShowLoadingScreen()
        }
        UiState.ERROR -> AlertDialogError(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se pueden cargar las estadísticas en estos momentos.",
            onConfirmation = { }
        )
        UiState.SUCCESS -> ShowDashboardScreen(viewModel = viewModel)
    }
}

@Composable
fun ShowDashboardScreen(viewModel: DashboardViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(bottom = 70.dp)
    ) {
        InsertTitle("Panel de Estadísticas")
        HorizontalLine(color = Color.LightGray)
        FilterDashboardBar(viewModel)
        ShowStatistics(viewModel)
    }
}

