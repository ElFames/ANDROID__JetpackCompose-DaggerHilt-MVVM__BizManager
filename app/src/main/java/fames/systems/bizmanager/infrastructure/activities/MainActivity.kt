package fames.systems.bizmanager.infrastructure.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import fames.systems.bizmanager.application.BizManagerApp
import fames.systems.bizmanager.infrastructure.resources.BizManagerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizManagerTheme {
                BizManagerApp()
            }
        }
    }

}