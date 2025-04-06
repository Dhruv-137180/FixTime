package com.bat_50.fixtime.di

import android.content.Context
import androidx.room.Room
import com.bat_50.fixtime.data.AppDatabase
import com.bat_50.fixtime.data.pomodoro.PomodoroDao
import com.bat_50.fixtime.data.task.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "task_database"
        )
            .fallbackToDestructiveMigration() // ✅ Add this line
            .build()
    }


    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao {
        return db.taskDao()
    }
    @Provides
    fun providePomodoroDao(db: AppDatabase): PomodoroDao {
        return db.pomodoroDao()
    }

}
