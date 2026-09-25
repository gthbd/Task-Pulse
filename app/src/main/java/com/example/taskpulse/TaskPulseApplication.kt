package com.example.taskpulse

import android.app.Application
import com.example.taskpulse.data.AppDatabase
import com.example.taskpulse.data.TaskRepository

class TaskPulseApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}