package fames.systems.bizmanager.application.dashboard.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import fames.systems.bizmanager.R

@Composable
fun DashboardCard(title: String, content: List<String>, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, color = Color.Gray, shape = MaterialTheme.shapes.small),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )

            if (content.isEmpty()) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation_circle))
                var isPlaying by rememberSaveable { mutableStateOf(true) }
                val progress by animateLottieCompositionAsState(composition = composition, isPlaying = isPlaying)

                LottieAnimation(
                    modifier = Modifier.size(50.dp),
                    composition = composition,
                    progress = {progress}
                )
                LaunchedEffect(key1 = progress) {
                    if (progress == 1f) isPlaying = false
                    if (progress == 0f) isPlaying = true
                }
            } else {
                content.forEach { item ->
                    Text(
                        text = item,
                        style = TextStyle(fontSize = 18.sp, color = Color.Black),
                        modifier = Modifier.padding(bottom = 4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}