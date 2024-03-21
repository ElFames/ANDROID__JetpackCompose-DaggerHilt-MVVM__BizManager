package fames.systems.bizmanager.application.products.ui.newproducts.components

import android.net.Uri
import android.os.Build
import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import fames.systems.bizmanager.R
import fames.systems.bizmanager.infrastructure.utils.values.BoldText

@Composable
fun PreviewSelectedImage(imageUri: Uri?) {
    Spacer(modifier = Modifier.height(25.dp))
    BoldText(text = "Imagen del producto")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        if (imageUri != null) {
            val context = LocalContext.current
            val bitmap = context.contentResolver.loadThumbnail(imageUri, Size(240, 240), null)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Selected Image",
                modifier = Modifier
                    .size(240.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.auth_header_image),
                contentDescription = "Placeholder Image",
                modifier = Modifier
                    .size(240.dp)
            )
        }
    } else {
        if (imageUri != null) {
            Image(
                painter = rememberImagePainter(data = imageUri),
                contentDescription = "Selected Image",
                modifier = Modifier
                    .size(240.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.auth_header_image),
                contentDescription = "Placeholder Image",
                modifier = Modifier
                    .size(240.dp)
            )
        }
    }
}
