package fames.systems.bizmanager.application.auth.ui.register.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomInfoText() {
    Text(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        text = "BizManager es una herramienta totalmente gratuita para emprendedores. Empieza a digitalizar tu negocio, reduce tus perdidas y aumenta tus beneficios!",
        color = Color.White,
        fontFamily = FontFamily.Serif,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
}