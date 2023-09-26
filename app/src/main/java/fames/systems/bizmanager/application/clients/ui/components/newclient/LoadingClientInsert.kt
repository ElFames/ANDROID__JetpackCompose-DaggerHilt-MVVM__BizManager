package fames.systems.bizmanager.application.clients.ui.components.newclient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import fames.systems.bizmanager.R

@Composable
fun LoadingClientInsert() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation_circle))
    var isPlaying by rememberSaveable { mutableStateOf(true) }
    val progress by animateLottieCompositionAsState(composition = composition, isPlaying = isPlaying)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(300.dp),
            composition = composition,
            progress = {progress}
        )
        LaunchedEffect(key1 = progress) {
            if (progress == 1f) isPlaying = false
            if (progress == 0f) isPlaying = true
        }
    }
}