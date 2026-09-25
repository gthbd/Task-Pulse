package com.example.taskpulse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskpulse.data.RetrofitInstance
import com.example.taskpulse.data.TaskRepository
import com.example.taskpulse.model.QuoteUiState
import com.example.taskpulse.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks: StateFlow<List<Task>> = repository.allTasks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleDone(task: Task) {
        viewModelScope.launch { repository.update(task.copy(isDone = !task.isDone)) }
    }

    fun addTask(task: Task) {
        viewModelScope.launch { repository.insert(task) }
    }
    fun updateTask(task: Task) {
        viewModelScope.launch { repository.update(task) }
    }
    fun deleteTask(task: Task) {
        viewModelScope.launch { repository.delete(task) }
    }



    //state và hàm fetch cho retrofit
    private val _quoteState = MutableStateFlow<QuoteUiState>(QuoteUiState.Loading)
    val quoteState: StateFlow<QuoteUiState> = _quoteState

    init {
        fetchQuote()
    }
    fun fetchQuote() {
        viewModelScope.launch {
            _quoteState.value = QuoteUiState.Loading
            try {
                val quote = RetrofitInstance.api.getRandomQuote()
                _quoteState.value = QuoteUiState.Success(quote)
            } catch (e: Exception) {
                _quoteState.value = QuoteUiState.Error(e.message ?: "Không tải được quote")
            }
        }
    }
}
