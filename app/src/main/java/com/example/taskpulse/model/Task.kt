package com.example.taskpulse.model

enum class Priority { LOW, MEDIUM, HIGH }

data class Task(
    val id: Int,
    val title: String,
    val category: String,
    val priority: Priority,
    val isDone: Boolean = false
)

// Dữ liệu giả để dựng UI trước khi nối Room — xóa khi có ViewModel + DB thật.
val mockTasks = listOf(
    Task(1, "Hoàn thành Unit 4 Compose Navigation", "Học tập", Priority.HIGH),
    Task(2, "Setup Room database cho TaskPulse", "Lập trình", Priority.HIGH),
    Task(3, "Đi chợ mua đồ ăn tuần", "Cá nhân", Priority.MEDIUM),
    Task(4, "Đọc 10 trang sách", "Thói quen", Priority.LOW, isDone = true),
    Task(5, "Tập thể dục 30 phút", "Thói quen", Priority.MEDIUM)
)