package com.bat_50.fixtime.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bat_50.fixtime.ui.pomodoro.PomodoroViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@Composable
fun ProfileScreen(viewModel: PomodoroViewModel = hiltViewModel()) {
    val sessions by viewModel.allSessions.collectAsState()

    val totalToday = remember { viewModel.getFocusTimeToday() / 1000 / 60 } // in minutes

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Focus Summary", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("Today’s Focus Time: ${totalToday} mins", style = MaterialTheme.typography.titleMedium)
            }
        }

        Spacer(Modifier.height(24.dp))
        Text("Pomodoro History", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(8.dp))

        sessions.sortedByDescending { it.startTime }.forEach { session ->
            val start = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date(session.startTime))
            val duration = ((session.endTime - session.startTime) / 1000 / 60.0).roundToInt()

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text("Date: $start", style = MaterialTheme.typography.bodyLarge)
                    Text("Duration: $duration mins", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
