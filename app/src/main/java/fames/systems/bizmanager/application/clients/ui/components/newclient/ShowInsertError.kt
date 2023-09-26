package fames.systems.bizmanager.application.clients.ui.components.newclient

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ShowInsertError(navController: NavHostController, hideError: () -> Unit) {
    AlertDialog(
        icon = { Icon(Icons.Default.Warning, contentDescription = "warning icon") },
        title = { Text(text = "Error") },
        text = { Text(text = "No se ha podido añadir al cliente, inténtalo de nuevo más tarde") },
        onDismissRequest = { navController.popBackStack() },
        confirmButton = {
            TextButton(
                onClick = {
                    hideError()
                }
            ) {
                Text("OK")
            }
        }
    )
}