package com.example.taskpulse.data

import androidx.room.Dao
import androidx.room.Query
import com.taskpulse.app.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("Select * from TaskEntity")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE category = :category")
    fun getTasksByCategory(category: String): Flow<List<TaskEntity>>

}