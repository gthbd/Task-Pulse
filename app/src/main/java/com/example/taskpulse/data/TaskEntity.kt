package com.example.taskpulse.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val category: String,
    @ColumnInfo val priority: Int = 2,
    @ColumnInfo val isDone: Boolean = false
)



