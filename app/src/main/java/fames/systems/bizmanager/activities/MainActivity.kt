package fames.systems.bizmanager.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import fames.systems.bizmanager.navigation.AppNavigation
import fames.systems.bizmanager.resources.BizManagerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizManagerTheme {
                AppNavigation()
            }
        }
    }

}