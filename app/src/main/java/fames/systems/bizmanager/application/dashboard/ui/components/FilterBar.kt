package fames.systems.bizmanager.application.dashboard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fames.systems.bizmanager.R
import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import fames.systems.bizmanager.application.dashboard.ui.DashboardViewModel

@Composable
fun FilterBar(viewModel: DashboardViewModel, showCharts: Boolean, changeShowChartState: (Boolean) -> Unit) {
    var selectedFilter by rememberSaveable { mutableStateOf(Filter.DIA) }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Text(textAlign = TextAlign.Center,
                modifier = Modifier.width(75.dp),
                text = selectedFilter.filterName,
                color = Color.LightGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(10.dp))
            FilterButton {
                selectedFilter = when (selectedFilter) {
                    Filter.DIA -> Filter.SEMANA
                    Filter.SEMANA -> Filter.MES
                    else -> Filter.DIA
                }
                viewModel.updateFilterStatistics(selectedFilter)
            }
        }

        item {
            InfoButton(showCharts) { changeShowChartState(false) }
            Spacer(modifier = Modifier.width(12.dp))
            ChartButton(showCharts) { changeShowChartState(true) }
        }

    }
}

@Composable
fun InfoButton(showCharts: Boolean, onClickInfo: () -> Unit) {
    val icon = if (showCharts) R.drawable.grid_icon else R.drawable.grid_icon_green
    Card(colors = CardDefaults.cardColors(containerColor = Color.Gray), shape = CircleShape) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .size(27.4.dp)
                .clickable { onClickInfo() },
            painter = painterResource(id = icon),
            contentDescription = "info icon"
        )
    }
}

@Composable
fun ChartButton(showCharts: Boolean, onClickChart: () -> Unit) {
    val icon = if (!showCharts) R.drawable.chart_icon else R.drawable.chart_icon_green
    Card(colors = CardDefaults.cardColors(containerColor = Color.Gray), shape = CircleShape) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .size(28.dp)
                .clickable { onClickChart() },
            painter = painterResource(id = icon),
            contentDescription = "chart icon"
        )
    }
}

@Composable
fun FilterButton(onClickFilterButton: () -> Unit) {
    Card(colors = CardDefaults.cardColors(containerColor = Color.Gray), shape = CircleShape) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .size(26.dp)
                .clickable { onClickFilterButton() },
            painter = painterResource(id = R.drawable.filter_icon),
            contentDescription = "info icon"
        )
    }
}