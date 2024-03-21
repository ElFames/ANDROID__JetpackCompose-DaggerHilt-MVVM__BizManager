package fames.systems.bizmanager.application.clients.ui.newclient.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.infrastructure.resources.buttonColor

@Composable
fun SaveButton(insertEnable: Boolean, onSaveClient: () -> Unit) {
    Button(modifier = Modifier.padding(16.dp).width(Dp.Infinity),
        onClick = { onSaveClient() },
        enabled = insertEnable,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp,
            pressedElevation = 1.dp,
            focusedElevation = 10.dp,
            hoveredElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        Text(text = "Guardar")
    }
}