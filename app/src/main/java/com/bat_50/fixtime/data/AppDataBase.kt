package com.bat_50.fixtime.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.bat_50.fixtime.data.task.Task
import com.bat_50.fixtime.data.task.TaskDao

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
