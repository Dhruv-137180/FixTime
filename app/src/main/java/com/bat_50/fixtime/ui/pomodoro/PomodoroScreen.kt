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
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@Composable
fun PomodoroScreen(
    viewModel: PomodoroViewModel = hiltViewModel()
) {
    val timeLeft by viewModel.timeLeft
    val isRunning = viewModel.isTimerRunning()
    val isPaused = viewModel.isTimerPaused()

    val minutes = (timeLeft / 1000) / 60
    val seconds = (timeLeft / 1000) % 60

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = String.format("%02d:%02d", minutes, seconds),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        when {
            !isRunning && !isPaused -> {
                Button(onClick = { viewModel.startTimer() }) {
                    Text("Start")
                }
            }
            isRunning -> {
                Button(onClick = { viewModel.pauseTimer() }) {
                    Text("Pause")
                }
            }
            isPaused -> {
                Button(onClick = { viewModel.resumeTimer() }) {
                    Text("Resume")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.resetTimer() }) {
            Text("Reset")
        }
    }
}
