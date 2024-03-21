package fames.systems.bizmanager.application.products.ui.newproducts.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import fames.systems.bizmanager.application.products.ui.newproducts.NewProductViewModel
import fames.systems.bizmanager.infrastructure.resources.buttonColor
import fames.systems.bizmanager.infrastructure.resources.disabledButtonColor


@Composable
fun OnSaveProductButton(insertEnable: Boolean, imageUri: Uri?, viewModel: NewProductViewModel) {
    val context = LocalContext.current.applicationContext
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            enabled = insertEnable && imageUri != null,
            onClick = {
                viewModel.onSaveProduct(imageUri!!, context)
            },
            shape = ShapeDefaults.Small,
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 17.dp, vertical = 7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = Color.White,
                disabledContainerColor = disabledButtonColor,
                disabledContentColor = Color.White
            )
        ) {
            Text(text = "Agregar Producto")
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}