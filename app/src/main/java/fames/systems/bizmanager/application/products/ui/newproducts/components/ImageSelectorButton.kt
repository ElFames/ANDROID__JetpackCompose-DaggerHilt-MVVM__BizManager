package fames.systems.bizmanager.application.products.ui.newproducts.components

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ImageSelectorButton(galleryLauncher: ManagedActivityResultLauncher<String, Uri?>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { galleryLauncher.launch("image/*") },
            enabled = true,
            shape = ShapeDefaults.Small,
            modifier = Modifier
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.LightGray
            )
        ) {
            Text(text = "Seleccionar Imagen")
        }
    }
    Spacer(modifier = Modifier.height(50.dp))
}