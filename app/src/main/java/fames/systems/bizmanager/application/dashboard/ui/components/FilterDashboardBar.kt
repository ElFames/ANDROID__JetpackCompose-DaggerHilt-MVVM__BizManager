package fames.systems.bizmanager.application.dashboard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
fun FilterDashboardBar(viewModel: DashboardViewModel) {
    val selectedFilter by viewModel.filter.observeAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.width(75.dp),
            text = selectedFilter?.filterName ?: Filter.DIA.filterName,
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(10.dp))
        FilterButton(selectedFilter ?: Filter.DIA) { viewModel.updateFilterStatistics(it) }
    }
}

@Composable
fun FilterButton(selectedFilter: Filter, changeFilter: (Filter) -> Unit) {
    Card(
        Modifier.clickable {
            when (selectedFilter) {
                Filter.DIA -> changeFilter(Filter.SEMANA)
                Filter.SEMANA -> changeFilter(Filter.MES)
                Filter.MES -> changeFilter(Filter.DIA)
            }
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        shape = CircleShape
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)
                .size(32.dp),
            painter = painterResource(id = R.drawable.filter_icon),
            contentDescription = "info icon"
        )
    }
}