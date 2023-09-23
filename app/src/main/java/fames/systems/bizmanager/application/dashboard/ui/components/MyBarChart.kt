package fames.systems.bizmanager.application.dashboard.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.bar.SimpleBarDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation

@Composable
fun MyBarChart() {
    Column(modifier = Modifier.padding(16.dp)) {
        BarChart(
            barChartData = BarChartData(
                bars = listOf(
                    BarChartData.Bar(label = "Bar Label", value = 100f, color = Color.Red),
                    BarChartData.Bar(label = "Bar Label", value = 85f, color = Color.Green)
                ),
                startAtZero = true
            ),
            modifier = Modifier.fillMaxWidth().height(350.dp).padding(16.dp),
            animation = simpleChartAnimation(),
            barDrawer = SimpleBarDrawer(),
            xAxisDrawer = SimpleXAxisDrawer(),
            yAxisDrawer = SimpleYAxisDrawer(),
            labelDrawer = SimpleValueDrawer()
        )
    }
}