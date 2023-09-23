package fames.systems.bizmanager.application.auth.ui.register.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AlertDialogRegisterError(onConfirmation: () -> Unit, icon: ImageVector) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "warning icon")
        },
        title = {
            Text(text = "Error en el Registro")
        },
        text = {
            Text(text =  "El email ya está en uso")
        },
        onDismissRequest = {
            onConfirmation()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("OK")
            }
        }
    )
}