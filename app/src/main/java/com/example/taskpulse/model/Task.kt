package com.example.taskpulse.model

data class Task(
    val id: Int,
    val title: String,
    val category: String,
    val priority: Int, // 1 = LOW, 2 = MEDIUM, 3 = HIGH
    val isDone: Boolean = false
)

// Dữ liệu giả để dựng UI trước khi nối Room — xóa khi có ViewModel + DB thật.
val mockTasks = listOf(
    Task(1, "Hoàn thành Unit 4 Compose Navigation", "Học tập", 3),
    Task(2, "Setup Room database cho TaskPulse", "Lập trình", 3),
    Task(3, "Đi chợ mua đồ ăn tuần", "Cá nhân", 2),
    Task(4, "Đọc 10 trang sách", "Thói quen", 1, isDone = true),
    Task(5, "Tập thể dục 30 phút", "Thói quen", 2)
)