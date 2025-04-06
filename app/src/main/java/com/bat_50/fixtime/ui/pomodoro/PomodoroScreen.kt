package com.bat_50.fixtime.ui.pomodoro

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.time.Duration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomodoroScreen() {
    val totalDuration = Duration.ofMinutes(25)
    var remainingTime by remember { mutableStateOf(totalDuration) }
    var isRunning by remember { mutableStateOf(false) }

    // For sound at the end (optional)
    var mediaPlayer: MediaPlayer? = null

    // Timer logic
    LaunchedEffect(isRunning) {
        while (isRunning && !remainingTime.isZero && !remainingTime.isNegative) {
            delay(1000)
            remainingTime = remainingTime.minusSeconds(1)

            // When finished, stop and play sound
            if (remainingTime.isZero) {
                isRunning = false
                // TODO: Play a ringtone or show notification
                // mediaPlayer = MediaPlayer.create(context, R.raw.ringtone)
                // mediaPlayer?.start()
            }
        }
    }

    val totalSeconds = totalDuration.seconds.toFloat()
    val remainingSeconds = remainingTime.seconds.toFloat()
    val progress = (remainingSeconds / totalSeconds).coerceIn(0f, 1f) // ✅ FIXED division here

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pomodoro Timer") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                progress = progress,
                strokeWidth = 8.dp,
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = String.format(
                    "%02d:%02d",
                    remainingTime.toMinutesPart(),
                    remainingTime.toSecondsPart()
                ),
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { isRunning = !isRunning }) {
                Text(if (isRunning) "Pause" else "Start")
            }
        }
    }
}
