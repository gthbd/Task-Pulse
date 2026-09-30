package com.example.taskpulse.data

import com.example.taskpulse.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()
        .map { entityList -> entityList.map { it.toTask() } }

    /*
    suspend fun insert(task: Task) = taskDao.insert(task.toEntity())
    suspend fun update(task: Task) = taskDao.update(task.toEntity())
    suspend fun delete(task: Task) = taskDao.delete(task.toEntity())
     */
    suspend fun insert(task: Task) = taskDao.insert(
        title = task.title,
        category = task.category,
        priority = task.priority,
        isDone = task.isDone
    )

    suspend fun update(task: Task) = taskDao.update(
        id = task.id,
        title = task.title,
        category = task.category,
        priority = task.priority,
        isDone = task.isDone
    )

    suspend fun delete(task: Task) = taskDao.delete(
        id = task.id
    )

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

fun priorityConverter(priorityInt: Int): String {
    return when (priorityInt) {
        1 -> "Thấp"
        2 -> "Trung bình"
        3 -> "Cao"
        else -> "Trung bình"
    }
}

fun priorityToInt(priorityStr: String): Int {
    return when (priorityStr.lowercase()) {
        "thấp", "thấp", "1" -> 1
        "cao", "cao", "3" -> 3
        else -> 2
    }
}

