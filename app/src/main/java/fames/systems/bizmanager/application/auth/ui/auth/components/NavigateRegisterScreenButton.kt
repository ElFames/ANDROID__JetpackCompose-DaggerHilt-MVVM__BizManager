package fames.systems.bizmanager.application.auth.ui.auth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.navigation.screenRoutes.AppScreens
import fames.systems.bizmanager.resources.authBackgroundColor
import fames.systems.bizmanager.resources.buttonColor

@Composable
fun NavigateRegisterScreenButton(navController: NavHostController) {
    Button(
        shape = ShapeDefaults.Small,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 25.dp, vertical = 7.dp),
        onClick = {
            navController.navigate(AppScreens.RegisterScreen.route)
        },
        border = BorderStroke(1.dp, buttonColor),
        colors = ButtonDefaults.buttonColors(
            containerColor = authBackgroundColor,
            contentColor = buttonColor
        )
    ) {
        Text(
            text = "CREAR CUENTA",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )
    }
}