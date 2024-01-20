package fames.systems.bizmanager.application.auth.ui.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import fames.systems.bizmanager.application.auth.ui.shared.HeaderImage
import fames.systems.bizmanager.infrastructure.resources.authBackgroundColor


@Composable
fun ShowLoginErrorDialog(
    onConfirmation: () -> Unit,
    icon: ImageVector
) {
    Column(
        modifier = Modifier
            .background(color = authBackgroundColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        HeaderImage()
        AlertDialog(
            icon = {
                Icon(icon, contentDescription = "warning icon")
            },
            title = {
                Text(text = "Error de Autenticación")
            },
            text = {
                Text(text = "Las credenciales no són correctas")
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
}