package fames.systems.bizmanager.application.dashboard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fames.systems.bizmanager.R
import fames.systems.bizmanager.resources.authBackgroundColor
import fames.systems.bizmanager.resources.buttonColor

@Composable
fun TopBar(changeNavMenuState: () -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray)
            .padding(22.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item { MenuButton { changeNavMenuState() } }
        item { DashboardTitle() }
    }
}

@Composable
private fun MenuButton(changeMenuState: () -> Unit) {
    Card(shape = MaterialTheme.shapes.small, colors = CardDefaults.cardColors(containerColor = authBackgroundColor)) {
        Image(
            modifier = Modifier
                .size(35.dp)
                .padding(5.dp)
                .clickable { changeMenuState() },
            painter = painterResource(R.drawable.menu_icon),
            contentDescription = "menu icon",
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
private fun DashboardTitle() {
    Text(
        text = "Panel de Estadísticas",
        style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic
        ),
        color = buttonColor
    )
}