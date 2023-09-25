package fames.systems.bizmanager.application.auth.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import fames.systems.bizmanager.R
import fames.systems.bizmanager.resources.authBackgroundColor

@Composable
fun ShowLoadingScreen() {
    var isPlaying by rememberSaveable { mutableStateOf(true) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation_circle))
    val progress by animateLottieCompositionAsState(composition = composition, isPlaying = isPlaying)

    Column(
        modifier = Modifier
            .background(color = authBackgroundColor)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderImage()
        LottieAnimation(
            modifier = Modifier.size(150.dp),
            composition = composition,
            progress = { progress },
            renderMode = RenderMode.AUTOMATIC,
        )
        LaunchedEffect(key1 = progress) {
            if (progress == 1f) isPlaying = false
            if (progress == 0f) isPlaying = true
            if (!isPlaying) isPlaying = true
        }
    }
}