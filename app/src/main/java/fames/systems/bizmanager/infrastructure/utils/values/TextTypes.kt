package fames.systems.bizmanager.infrastructure.utils.values

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegularText(text: String) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = text,
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        fontSize = 15.sp
    )
}
@Composable
fun BoldText(text: String) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = text,
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
    )
}
