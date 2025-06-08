package com.example.todolistapp.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "isCompleted") val isCompleted: Boolean
)

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task): Long

    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<Task>>

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}