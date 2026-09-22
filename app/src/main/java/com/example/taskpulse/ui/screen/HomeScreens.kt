package com.taskpulse.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskpulse.R
import com.taskpulse.app.model.Task
import com.taskpulse.app.model.mockTasks
import com.taskpulse.app.ui.components.TaskItem
import com.taskpulse.app.ui.theme.TaskPulseTheme

// Đầu trang hiển thị quote/weather lấy từ REST API — hiện tại truyền text tĩnh,
// sau này thay bằng state từ ViewModel gọi Retrofit.
@Composable
private fun DailyPulseStrip(message: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Text(text = "Hôm nay", style = MaterialTheme.typography.labelSmall)
        Text(text = message, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun HomeScreen(
    tasks: List<Task>,
    onToggleDone: (Task) -> Unit,
    onAddTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddTaskClick,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = null
                    )
                       },
                text = { Text("Task mới") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(
                    text = "Chào Hiệp",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            item {
                DailyPulseStrip(message = "\"Việc hôm nay chớ để ngày mai.\"")
            }
            items(tasks, key = { it.id }) { task ->
                TaskItem(task = task, onToggleDone = onToggleDone)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    var tasks by remember { mutableStateOf(mockTasks) }
    TaskPulseTheme {
        HomeScreen(
            tasks = tasks,
            onToggleDone = { toggled ->
                tasks = tasks.map {
                    if (it.id == toggled.id) it.copy(isDone = !it.isDone) else it
                }
            },
            onAddTaskClick = {}
        )
    }
}