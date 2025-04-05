package com.bat_50.fixtime.data.task

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val dao: TaskDao) {
    fun getTasks(): Flow<List<Task>> = dao.getAllTasks()
    suspend fun insert(task: Task) = dao.insertTask(task)
    suspend fun delete(task: Task) = dao.deleteTask(task)
    suspend fun update(task: Task) = dao.updateTask(task)
}
