package fames.systems.bizmanager.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import dagger.hilt.android.AndroidEntryPoint
import fames.systems.bizmanager.navigation.AppNavigation
import fames.systems.bizmanager.resources.BizManagerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizManagerTheme {
                Scaffold {
                    // add bottom navigation
                    AppNavigation()
                }
            }
        }
    }

}