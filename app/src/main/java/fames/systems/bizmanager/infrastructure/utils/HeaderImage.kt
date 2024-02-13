package fames.systems.bizmanager.infrastructure.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.R

@Composable
fun HeaderImage() {
    Image(
        painter = painterResource(id = R.drawable.auth_header_image),
        contentDescription = "Imagen de encabezado de BizManager",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().height(200.dp)
    )
}
