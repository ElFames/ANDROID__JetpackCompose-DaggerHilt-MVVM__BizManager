package fames.systems.bizmanager.application.auth.ui.login.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

@Composable
fun ForgotPasswordButton() {
    TextButton(onClick = {}) {
        Text(
            text = "Tienes problemas para acceder? Pulsa aqui",
            color = Color.White,
            fontFamily = FontFamily.Serif
        )
    }
}