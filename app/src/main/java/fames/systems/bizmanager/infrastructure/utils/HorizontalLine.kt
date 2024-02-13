package fames.systems.bizmanager.infrastructure.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalLine(color: Color) {
    Spacer(modifier = Modifier
        .border(0.5.dp, color)
        .fillMaxWidth()
        .padding(0.5.dp))
}