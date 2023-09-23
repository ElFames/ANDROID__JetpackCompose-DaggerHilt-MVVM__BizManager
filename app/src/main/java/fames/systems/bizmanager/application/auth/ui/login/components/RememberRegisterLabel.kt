package fames.systems.bizmanager.application.auth.ui.login.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.navigation.AppScreens
import fames.systems.bizmanager.resources.buttonColor

@Composable
fun RememberRegisterLabel(navController: NavHostController) {
    TextButton(
        onClick = { navController.navigate(AppScreens.RegisterScreen.route) },
        modifier = Modifier.padding(80.dp, 0.dp, 0.dp, 0.dp)
    ) {
        Text(
            text = "No tienes cuenta todavía? Registrate",
            color = buttonColor,
            fontFamily = FontFamily.Serif
        )
    }
}