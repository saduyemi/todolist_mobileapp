package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapp.data.TaskRepositoryProvider
import com.example.todolistapp.ui.components.TaskScreen
import com.example.todolistapp.ui.components.TodoViewModel
import com.example.todolistapp.ui.components.TodoViewModelFactory
import com.example.todolistapp.ui.theme.TodoListAppTheme

class MainActivity : ComponentActivity() {
    //private val viewModel by viewModels<TodoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val taskRepository = TaskRepositoryProvider.provideRepository(applicationContext)
        val todoViewModelFactory =  TodoViewModelFactory(taskRepository)

        setContent {
            TodoListAppTheme {
                val viewModel : TodoViewModel = viewModel(factory = todoViewModelFactory)
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TaskScreen(viewModel = viewModel)
                }
            }
        }
    }
}


