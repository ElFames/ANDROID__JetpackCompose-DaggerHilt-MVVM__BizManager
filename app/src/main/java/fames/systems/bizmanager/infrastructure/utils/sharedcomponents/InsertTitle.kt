package fames.systems.bizmanager.infrastructure.utils.sharedcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fames.systems.bizmanager.infrastructure.utils.values.buttonColor

@Composable
fun InsertTitle(title: String, color: Color = Color.LightGray) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = color),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic
            ),
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
    }
}