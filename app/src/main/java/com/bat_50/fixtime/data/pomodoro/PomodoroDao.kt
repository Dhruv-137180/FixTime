package com.bat_50.fixtime.data.pomodoro


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PomodoroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: PomodoroSession)

    @Query("SELECT * FROM pomodoro_sessions")
    fun getAllSessions(): Flow<List<PomodoroSession>>
}
