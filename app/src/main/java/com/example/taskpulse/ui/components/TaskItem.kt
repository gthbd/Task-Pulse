package com.example.taskpulse.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskpulse.model.Priority
import com.example.taskpulse.model.Task
import com.example.taskpulse.ui.theme.Pulse
import com.example.taskpulse.ui.theme.PulseLow
import com.example.taskpulse.ui.theme.PulseMedium
import com.example.taskpulse.ui.theme.TaskPulseTheme

private fun priorityColor(priority: Priority): Color = when (priority) {
    Priority.HIGH -> Pulse
    Priority.MEDIUM -> PulseMedium
    Priority.LOW -> PulseLow
}

@Composable
fun TaskItem(
    task: Task,
    onToggleDone: (Task) -> Unit,
    onTaskClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), // dùng viền mảnh thay cho đổ bóng
        onClick = { onTaskClick(task) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Thanh màu bên trái mã hoá priority — thông tin, không phải trang trí
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(64.dp)
                    .background(priorityColor(task.priority))
            )

            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onToggleDone(task) }
            )

            Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None,
                    color = if (task.isDone) MaterialTheme.colorScheme.secondary
                    else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = task.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskItemPreview() {
    TaskPulseTheme {
        Column {
            TaskItem(
                task = Task(1, "Hoàn thành Unit 4 Compose Navigation", "Học tập", Priority.HIGH),
                onToggleDone = {},
                onTaskClick = {},
                modifier = Modifier.padding(8.dp)
            )
            TaskItem(
                task = Task(2, "Đọc 10 trang sách", "Thói quen", Priority.LOW, isDone = true),
                onToggleDone = {},
                onTaskClick = {},
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}