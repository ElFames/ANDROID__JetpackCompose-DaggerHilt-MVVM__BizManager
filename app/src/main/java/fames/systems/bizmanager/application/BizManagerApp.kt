package fames.systems.bizmanager.application

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fames.systems.bizmanager.infrastructure.navigation.bottombar.BottomBar
import fames.systems.bizmanager.infrastructure.navigation.graphs.AppNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BizManagerApp() {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {
        AppNavigation(navController)
    }
}