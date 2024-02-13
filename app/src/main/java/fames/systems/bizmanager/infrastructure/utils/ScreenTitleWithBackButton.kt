package fames.systems.bizmanager.infrastructure.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.clients.ui.clientdetail.components.CancelButton
import fames.systems.bizmanager.infrastructure.resources.buttonColor

@Composable
fun ScreenTitleWithBackButton(title: String, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CancelButton(navController = navController, Color.White)
        Text(
            modifier = Modifier.padding(16.dp),
            text = title,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic
            ),
            color = buttonColor
        )
    }
}
