package com.example.taskpulse.data

import com.example.taskpulse.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()
        .map { entityList -> entityList.map { it.toTask() } }

    suspend fun insert(task: Task) = taskDao.insert(task.toEntity())
    suspend fun update(task: Task) = taskDao.update(task.toEntity())
    suspend fun delete(task: Task) = taskDao.delete(task.toEntity())
}

fun TaskEntity.toTask(): Task = Task(
    id = id,
    title = title,
    category = category,
    priority = priority,
    isDone = isDone
)

fun Task.toEntity(): TaskEntity = TaskEntity(
    id = id,
    title = title,
    category = category,
    priority = priority,
    isDone = isDone
)

