package com.example.taskpulse.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskpulse.R
import com.example.taskpulse.data.priorityConverter
import com.example.taskpulse.model.Task
import com.example.taskpulse.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    taskId: Int, // -1 = thêm mới
    viewModel: TaskViewModel,
    onSaveClick: () -> Unit, // gọi sau khi lưu xong, để quay lại Home
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    val tasks by viewModel.tasks.collectAsState()
    val existingTask = tasks.find { it.id == taskId } // null nếu taskId = -1 hoặc không tìm thấy
    val context = androidx.compose.ui.platform.LocalContext.current

    var title by remember(existingTask) { mutableStateOf(existingTask?.title ?: "") }
    var isError by remember { mutableStateOf(false) } // biến kiểm tra lỗi
    var category by remember(existingTask) { mutableStateOf(existingTask?.category ?: "") }
    var priority by remember(existingTask) {
        mutableStateOf(
            existingTask?.priority ?: 2
        )
    }
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 48.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon (
                painter = painterResource(id = R.drawable.arrow_upward),
                contentDescription = "quay lại",
                modifier = Modifier
                    .rotate(270f)
                    .clickable { onBackClick() }
            )
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    if (it.isNotBlank()) isError = false
                                },
                label = { Text("Tiêu đề *") },
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(
                        text = "Tiêu đề không được để trống",
                        color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Phân loại") }
            )

            var priorityMenuExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = priorityMenuExpanded,
                onExpandedChange = { priorityMenuExpanded = it }
            ) {
                OutlinedTextField(
                    value = priorityConverter(priority),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Mức độ ưu tiên") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = priorityMenuExpanded)
                    },
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = priorityMenuExpanded,
                    onDismissRequest = { priorityMenuExpanded = false }
                ) {
                    val priorityList = listOf(
                        1 to "Thấp",
                        2 to "Trung bình",
                        3 to "Cao"
                    )
                    priorityList.forEach { (value, label) ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                priority = value
                                priorityMenuExpanded = false
                            }
                        )
                    }
                }
            }
            Row() {
                IconButton(
                    onClick = {
                        if (title.isBlank()) {
                            isError = true
                            Toast.makeText(context, "Tiêu đề không được để trống", Toast.LENGTH_SHORT).show()
                            return@IconButton
                        }
                        if (existingTask == null) {
                            viewModel.addTask(
                                Task(
                                    id = 0,
                                    title = title,
                                    category = category,
                                    priority = priority
                                )
                            )
                        } else {
                            viewModel.updateTask(
                                existingTask.copy(
                                    title = title,
                                    category = category,
                                    priority = priority
                                )
                            )
                        }
                        onSaveClick()
                    },
                ) {
                    Icon(
                        painter = painterResource(id = if (existingTask == null) R.drawable.add else R.drawable.save),
                        contentDescription = if (existingTask == null) "add" else "save",
                    )
                }
                if (existingTask != null) {
                    IconButton(onClick = {
                        viewModel.deleteTask(existingTask)
                        onSaveClick() // dùng lại để quay về Home sau khi xóa
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "delete")
                    }
                }
            }
        }
    }
}