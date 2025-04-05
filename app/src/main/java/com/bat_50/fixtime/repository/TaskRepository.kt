package com.bat_50.fixtime.repository

import com.bat_50.fixtime.data.task.Task
import com.bat_50.fixtime.data.task.TaskDao
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val tasks: Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) = taskDao.insert(task)
    suspend fun delete(task: Task) = taskDao.delete(task)
    suspend fun update(task: Task) = taskDao.update(task)
}
