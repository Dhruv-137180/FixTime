package com.bat_50.fixtime.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bat_50.fixtime.data.task.Task

@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()

    val (newTitle, setNewTitle) = remember { mutableStateOf("") }
    val (newDescription, setNewDescription) = remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (newTitle.isNotBlank()) {
                    viewModel.addTask(newTitle.trim(), newDescription.trim())
                    setNewTitle("")
                    setNewDescription("")
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)
        ) {
            OutlinedTextField(
                value = newTitle,
                onValueChange = setNewTitle,
                label = { Text("Task Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = newDescription,
                onValueChange = setNewDescription,
                label = { Text("Task Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Pending Tasks", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(tasks.filter { !it.isDone }) { task ->
                    TaskItem(task = task, onUpdate = viewModel::updateTask)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Completed Tasks", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(tasks.filter { it.isDone }) { task ->
                    TaskItem(task = task, onUpdate = viewModel::updateTask)
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onUpdate: (Task) -> Unit) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    onUpdate(task.copy(title = title, description = description))
                },
                textStyle = if (task.isDone) LocalTextStyle.current.copy(textDecoration = TextDecoration.LineThrough) else LocalTextStyle.current,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = {
                    description = it
                    onUpdate(task.copy(title = title, description = description))
                },
                textStyle = if (task.isDone) LocalTextStyle.current.copy(textDecoration = TextDecoration.LineThrough) else LocalTextStyle.current,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Checkbox(
            checked = task.isDone,
            onCheckedChange = { isChecked ->
                onUpdate(task.copy(isDone = isChecked))
            },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    onNavigateToPomodoro: () -> Unit
) {
    // Your task list UI...

    Button(
        onClick = onNavigateToPomodoro,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Start Pomodoro")
    }
}