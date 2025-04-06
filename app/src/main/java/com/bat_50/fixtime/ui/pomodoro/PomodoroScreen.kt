package com.bat_50.fixtime.ui.pomodoro

import android.media.RingtoneManager
import android.media.Ringtone
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@Composable
fun PomodoroScreen() {
    val context = LocalContext.current
    var timeInMinutes by remember { mutableStateOf(25) }
    var remainingTime by remember { mutableStateOf(timeInMinutes * 60) }
    var isRunning by remember { mutableStateOf(false) }
    var isFinished by remember { mutableStateOf(false) }

    val ringtone: Ringtone by remember {
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        mutableStateOf(RingtoneManager.getRingtone(context, notification))
    }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (remainingTime > 0) {
                delay(1000L)
                remainingTime--
            }
            isRunning = false
            isFinished = true
            ringtone.play()
        }
    }

    fun formatTime(seconds: Int): String {
        val minutes = TimeUnit.SECONDS.toMinutes(seconds.toLong())
        val secs = seconds % 60
        return String.format("%02d:%02d", minutes, secs)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Pomodoro Timer",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = formatTime(remainingTime),
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (!isRunning && !isFinished) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Focus Time (min): ", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Slider(
                    value = timeInMinutes.toFloat(),
                    onValueChange = {
                        timeInMinutes = it.toInt()
                        remainingTime = timeInMinutes * 60
                    },
                    valueRange = 5f..60f,
                    steps = 11
                )
                Text("$timeInMinutes", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                isRunning = true
                isFinished = false
                remainingTime = timeInMinutes * 60
            },
            enabled = !isRunning
        ) {
            Text("Start Focus")
        }

        if (isFinished) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Time's up!", color = Color.Red, fontWeight = FontWeight.Bold)
        }
    }
}