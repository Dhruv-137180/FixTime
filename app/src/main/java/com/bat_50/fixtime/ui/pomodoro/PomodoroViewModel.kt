package com.bat_50.fixtime.ui.pomodoro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bat_50.fixtime.data.pomodoro.PomodoroRepository
import com.bat_50.fixtime.data.pomodoro.PomodoroSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PomodoroViewModel @Inject constructor(
    private val repository: PomodoroRepository
) : ViewModel() {

    var timeLeft by mutableStateOf(1500) // 25 minutes
        private set

    val allSessions: StateFlow<List<PomodoroSession>> =
        repository.getAllSessions()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun tick() {
        if (timeLeft > 0) timeLeft--
    }

    fun reset() {
        timeLeft = 1500
    }

    fun setCustomTime(seconds: Int) {
        timeLeft = seconds
    }

    fun getFocusTimeToday(): Long {
        val now = System.currentTimeMillis()
        val startOfDay = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        return allSessions.value
            .filter { it.startTime >= startOfDay && it.endTime <= now }
            .sumOf { it.endTime - it.startTime }
    }
}
