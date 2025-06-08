package com.example.todolistapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.data.Task

@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    viewModel: TodoViewModel,
    checkTask: (Boolean) -> Unit,
) {
    var isEditable by remember { mutableStateOf(false) }

    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isEditable) {
            Text(
                text = task.title,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { checkTask(it) },
                modifier = Modifier
                    .padding(end = 8.dp)
            )

            Button(
                onClick = { isEditable = true },
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            ) {
                Text(text="Edit", fontSize = 10.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { viewModel.deleteTask(task.id) },
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            ) {
                Text(text = "Del", fontSize = 10.sp)
            }

        } else {
            var content by remember { mutableStateOf(task.title) }

            TextField(
                value = content,
                onValueChange = {content = it},
                modifier = Modifier
                    .weight(1f)
                    .padding(end=8.dp)
            )

            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = {},
                enabled = false,
                modifier = Modifier
                    .padding(end = 8.dp)
            )

            Button(
                onClick = {
                    viewModel.renameTask(task.id, content)
                    content = ""
                    isEditable = false
                },
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            ) {
                Text(text = "Done", fontSize = 10.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { isEditable = false },
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            ) {
                Text(text = "Cancel", fontSize = 10.sp)
            }
        }
    }
}

