package fames.systems.bizmanager.application.clients.ui.clientdetail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CancelButton(navController: NavHostController, color: Color) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "back_button",
        modifier = Modifier
            .size(55.dp)
            .clickable { navController.popBackStack() }
            .padding(16.dp),
        tint = color
    )
}