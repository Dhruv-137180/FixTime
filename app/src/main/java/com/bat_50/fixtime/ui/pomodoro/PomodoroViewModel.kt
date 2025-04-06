package com.bat_50.fixtime.ui.pomodoro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bat_50.fixtime.data.pomodoro.PomodoroRepository
import com.bat_50.fixtime.data.pomodoro.PomodoroSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PomodoroViewModel @Inject constructor(
    private val pomodoroRepository: PomodoroRepository
) : ViewModel() {

    private var timer: CountDownTimer? = null
    private var _timeLeft = mutableStateOf(25 * 60 * 1000L) // 25 minutes
    val timeLeft: State<Long> = _timeLeft

    private var isRunning = mutableStateOf(false)
    private var isPaused = mutableStateOf(false)
    private var startTime: Long? = null
    private var pauseTimeLeft: Long = 0L

    fun startTimer(duration: Long = 25 * 60 * 1000L) {
        if (isRunning.value) return

        isRunning.value = true
        isPaused.value = false
        startTime = System.currentTimeMillis()

        timer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = millisUntilFinished
                pauseTimeLeft = millisUntilFinished
            }

            override fun onFinish() {
                isRunning.value = false
                savePomodoroSession(startTime!!, System.currentTimeMillis())
                _timeLeft.value = 0L
            }
        }.start()
    }

    fun pauseTimer() {
        if (!isRunning.value) return
        timer?.cancel()
        timer = null
        isRunning.value = false
        isPaused.value = true
    }

    fun resumeTimer() {
        if (isPaused.value) {
            startTimer(pauseTimeLeft)
        }
    }

    fun resetTimer() {
        timer?.cancel()
        timer = null
        isRunning.value = false
        isPaused.value = false
        _timeLeft.value = 25 * 60 * 1000L
    }

    private fun savePomodoroSession(start: Long, end: Long) {
        viewModelScope.launch {
            pomodoroRepository.insertSession(PomodoroSession(startTime = start, endTime = end))
        }
    }

    fun isTimerRunning(): Boolean = isRunning.value
    fun isTimerPaused(): Boolean = isPaused.value
}
