package com.example.taskpulse.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.taskpulse.model.Priority


@Entity(tableName = "Tasks")
@TypeConverters(PriorityConverter::class)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val category: String,
    @ColumnInfo val priority: Priority,
    @ColumnInfo val isDone: Boolean = false
)

class PriorityConverter {
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}

