package fames.systems.bizmanager.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint
import fames.systems.bizmanager.application.AppScreen
import fames.systems.bizmanager.application.auth.ui.auth.AuthScreen
import fames.systems.bizmanager.application.auth.ui.login.LoginScreen
import fames.systems.bizmanager.application.auth.ui.login.LoginViewModel
import fames.systems.bizmanager.application.auth.ui.register.RegisterScreen
import fames.systems.bizmanager.application.auth.ui.register.RegisterViewModel
import fames.systems.bizmanager.navigation.screenRoutes.AppScreens
import fames.systems.bizmanager.resources.BizManagerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizManagerTheme {
                AppScreen()
            }
        }
    }

}