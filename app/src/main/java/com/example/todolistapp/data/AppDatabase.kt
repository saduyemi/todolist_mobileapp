package com.example.todolistapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}

object TodoDatabaseProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE != null) {
            return INSTANCE as AppDatabase
        }

        synchronized(this) {
            if (INSTANCE == null) {
                val database = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "todo_database").build()

                // Assign the newly created database to INSTANCE
                INSTANCE = database
            }
            return INSTANCE as AppDatabase
        }
    }
}
