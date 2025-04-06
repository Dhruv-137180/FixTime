package com.bat_50.fixtime.data.pomodoro


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PomodoroRepository @Inject constructor(
    private val dao: PomodoroDao
) {
    suspend fun insertSession(session: PomodoroSession) = dao.insertSession(session)

    fun getAllSessions(): Flow<List<PomodoroSession>> = dao.getAllSessions()
}
