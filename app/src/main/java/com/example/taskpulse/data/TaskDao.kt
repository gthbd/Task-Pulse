package com.example.taskpulse.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("Select * from Tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("select * from Tasks where category = :category")
    fun getTasksByCategory(category: String): Flow<List<TaskEntity>>

    @Query("select * from Tasks where priority = :priority")
    fun getTasksByPriority(priority: Int): Flow<List<TaskEntity>>

    @Insert
    suspend fun insert(task: TaskEntity)

    @Update
    suspend fun update(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)
}
