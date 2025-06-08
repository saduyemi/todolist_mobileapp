package com.example.todolistapp.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    suspend fun insert(task: Task): Long = taskDao.insert(task)

    suspend fun update(task: Task) = taskDao.update(task)

    suspend fun delete(task: Task) = taskDao.delete(task)

    fun getAll(): Flow<List<Task>> = taskDao.getAll()
}

object TaskRepositoryProvider {
    fun provideRepository(context: Context): TaskRepository {
        val database = TodoDatabaseProvider.getDatabase(context)
        return TaskRepository(database.taskDao())
    }
}
