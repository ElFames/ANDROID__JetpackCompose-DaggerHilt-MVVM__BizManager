package fames.systems.bizmanager.application.auth.ui.register.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.navigation.screenRoutes.AppScreens
import fames.systems.bizmanager.resources.buttonColor

@Composable
fun RememberLoginLabel(navController: NavHostController) {
    TextButton(modifier = Modifier.padding(80.dp,0.dp,0.dp,0.dp),onClick = { navController.navigate(
        AppScreens.LoginScreen.route) }) {
        Text(
            text = "Ya estás registrado? Accede a tu cuenta",
            color = buttonColor,
            fontFamily = FontFamily.Serif
        )
    }
}