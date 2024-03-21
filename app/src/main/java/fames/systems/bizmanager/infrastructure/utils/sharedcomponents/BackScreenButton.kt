package fames.systems.bizmanager.infrastructure.utils.sharedcomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BackScreenButton(navController: NavController, color: Color) {
    Icon(
        imageVector = Icons.Outlined.ArrowBack,
        contentDescription = "back_button",
        modifier = Modifier
            .size(55.dp)
            .clickable { navController.popBackStack() }
            .padding(16.dp),
        tint = color
    )
}
