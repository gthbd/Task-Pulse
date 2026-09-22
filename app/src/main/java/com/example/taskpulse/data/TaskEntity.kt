package com.taskpulse.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Priority { LOW, MEDIUM, HIGH }

@Entity
data class TaskEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val category: String,
    @ColumnInfo val priority: Priority,
    @ColumnInfo val isDone: Boolean = false
)

// Dữ liệu giả để dựng UI trước khi nối Room — xóa khi có ViewModel + DB thật.
val mockTasks = listOf(
    TaskEntity(1, "Hoàn thành Unit 4 Compose Navigation", "Học tập", Priority.HIGH),
    TaskEntity(2, "Setup Room database cho TaskPulse", "Lập trình", Priority.HIGH),
    TaskEntity(3, "Đi chợ mua đồ ăn tuần", "Cá nhân", Priority.MEDIUM),
    TaskEntity(4, "Đọc 10 trang sách", "Thói quen", Priority.LOW, isDone = true),
    TaskEntity(5, "Tập thể dục 30 phút", "Thói quen", Priority.MEDIUM)
)