package com.example.taskpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskpulse.ui.screens.AddEditTaskScreen
import com.example.taskpulse.ui.screens.HomeScreen
import com.example.taskpulse.ui.theme.TaskPulseTheme
import com.example.taskpulse.viewmodel.TaskViewModel
import com.example.taskpulse.viewmodel.TaskViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskPulseTheme {
                val app = application as TaskPulseApplication
                val viewModel: TaskViewModel = viewModel(
                    factory = TaskViewModelFactory(app.repository)
                )
                val tasks by viewModel.tasks.collectAsState()
                val quoteState by viewModel.quoteState.collectAsState()

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable(
                        route ="home"
                    ) {
                        HomeScreen(
                            tasks = tasks,
                            quoteState = quoteState,
                            onToggleDone = viewModel::toggleDone,
                            onTaskClick = { task -> navController.navigate("add_edit_task?taskId=${task.id}") },
                            onAddTaskClick = { navController.navigate("add_edit_task") }
                        )
                    }
                    composable(
                        route ="add_edit_task?taskId={taskId}",
                        arguments = listOf(navArgument("taskId"){
                            type = NavType.IntType
                            defaultValue = -1
                        }),
                    ) { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
                        AddEditTaskScreen(
                            taskId = taskId,
                            viewModel = viewModel,
                            onSaveClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
