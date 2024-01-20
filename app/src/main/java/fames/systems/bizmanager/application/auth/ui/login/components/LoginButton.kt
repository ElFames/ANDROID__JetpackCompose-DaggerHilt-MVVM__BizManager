package fames.systems.bizmanager.application.auth.ui.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.infrastructure.resources.buttonColor
import fames.systems.bizmanager.infrastructure.resources.disabledButtonColor

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        enabled = loginEnable,
        shape = ShapeDefaults.Small,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 25.dp, vertical = 7.dp),
        onClick = { onLoginSelected() },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.White,
            disabledContainerColor = disabledButtonColor,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = "INICIAR SESIÓN",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )
    }
}