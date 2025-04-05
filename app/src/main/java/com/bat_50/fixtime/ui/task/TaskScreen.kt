package com.bat_50.fixtime.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bat_50.fixtime.data.task.Task
import com.bat_50.fixtime.ui.components.TaskCard

@Composable
fun TaskScreen(viewModel: TaskViewModel = hiltViewModel()) {
    val tasks by viewModel.tasks.collectAsState()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Tasks", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (title.isNotBlank()) {
                    viewModel.addTask(Task(title = title, description = description))
                    title = ""
                    description = ""
                }
            },
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(8.dp))

        tasks.forEach { task ->
            TaskCard(
                task = task,
                onToggleDone = { viewModel.toggleDone(it) },
                onDelete = { viewModel.deleteTask(it) }
            )
        }
    }
}
