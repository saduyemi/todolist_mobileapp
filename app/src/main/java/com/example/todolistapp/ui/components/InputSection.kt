package com.example.todolistapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InputSection(
    viewModel: TodoViewModel,
    modifier: Modifier = Modifier
) {
    val input by viewModel.input.collectAsState()

    Row(modifier = modifier) {
        OutlinedTextField(
            value = input,
            onValueChange = { viewModel.updateInput(it) }
        )

        Spacer(modifier = Modifier.width(15.dp))

        Button(onClick = {viewModel.addTask()}) {
            Text(text = "Add")
        }
    }
}