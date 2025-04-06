package com.bat_50.fixtime.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.bat_50.fixtime.data.task.Task
import com.bat_50.fixtime.data.task.TaskDao
import com.bat_50.fixtime.data.pomodoro.PomodoroSession
import com.bat_50.fixtime.data.pomodoro.PomodoroDao

@Database(
    entities = [Task::class, PomodoroSession::class], // 👈 include all your entities here
    version = 2, // 👈 bump this if you change schema
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun pomodoroDao(): PomodoroDao
}