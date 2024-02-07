package fames.systems.bizmanager.application.clients.ui.newclient.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun NewClientTitle() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Nuevo Cliente",
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        fontSize = 22.sp,
        fontStyle = FontStyle.Italic
    )
}