package com.example.todolistapp.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.Task
import com.example.todolistapp.data.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TaskRepository) : ViewModel() {
    private val _input = MutableStateFlow("")
    val input = _input.asStateFlow()

    val tasks = repository.getAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    fun updateInput(text: String) {
        _input.value = text
    }

    fun addTask() {
        val taskName = _input.value.trim()
        if (taskName.isNotEmpty()) {
            viewModelScope.launch {
                repository.insert(Task(title = taskName, isCompleted = false))
            }
            _input.value = ""
        }
    }

    fun renameTask(id: Int, name: String) {
       viewModelScope.launch {
           val updatedTask = tasks.value.find { it.id == id }?.copy(title = name)
           if (updatedTask != null) repository.update(updatedTask)
       }
    }

    fun checkTask(id: Int, isChecked: Boolean) {
        viewModelScope.launch {
            val task = tasks.value.find { it.id == id }
            if (task != null && task.isCompleted != isChecked) {
                repository.update(task.copy(isCompleted = isChecked))
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            tasks.value.find { it.id == id}?.let {
                repository.delete(it)
            }
        }
    }

    fun reset() {
       _input.value = ""
        viewModelScope.launch {
            tasks.value.forEach { repository.delete(it) }
        }
    }
}

class TodoViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
