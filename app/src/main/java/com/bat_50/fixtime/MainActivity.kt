package com.bat_50.fixtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bat_50.fixtime.ui.theme.FixTimeTheme
import java.text.SimpleDateFormat
import java.util.*
import org.burnoutcrew.reorderable.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.background
import androidx.compose.ui.input.pointer.pointerInput

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FixTimeTheme {
                TimeManagementApp()
            }
        }
    }
}

@Composable
fun TimeManagementApp() {
    Scaffold(
        topBar = { AppTopBar() },
        floatingActionButton = { AddTaskFab() }
    ) { paddingValues ->
        HomeScreenContent(modifier = Modifier.padding(paddingValues))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    TopAppBar(
        title = { Text(text = "Time Manager", fontSize = 20.sp) }
    )
}

@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    var taskList by remember { mutableStateOf((1..5).map { "Task $it" }) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello, User!", fontSize = 24.sp, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        CurrentTimeDisplay()
        Spacer(modifier = Modifier.height(16.dp))
        TaskList(
            taskList = taskList,
            onTaskEdited = { index, newText ->
                taskList = taskList.toMutableList().apply { set(index, newText) }
            },
            onReorder = { updatedList -> taskList = updatedList }
        )
    }
}


@Composable
fun CurrentTimeDisplay() {
    val currentTime = remember {
        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
    }
    Text(text = "Current Time: $currentTime", fontSize = 18.sp, style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun TaskList(taskList: List<String>, onTaskEdited: (Int, String) -> Unit, onReorder: (List<String>) -> Unit) {
    val reorderState = rememberReorderableLazyListState(
        onMove = { from, to ->
            val updatedList = taskList.toMutableList().apply {
                add(to.index, removeAt(from.index))
            }
            onReorder(updatedList)
        }
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .reorderable(reorderState)
            .detectReorderAfterLongPress(reorderState),
        state = reorderState.listState
    ) {
        itemsIndexed(taskList, key = { _, item -> item }) { index, task ->
            TaskItem(
                index = index,
                taskName = task,
                onTaskEdited = onTaskEdited,
                modifier = Modifier.draggedItem(reorderState, index)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )
        }
    }
}
@Composable
fun TaskItem(index: Int, taskName: String, onTaskEdited: (Int, String) -> Unit, modifier: Modifier = Modifier) {
    var isEditing by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(taskName) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { isEditing = true }, // Click to enter edit mode
        shape = MaterialTheme.shapes.medium
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            if (isEditing) {
                BasicTextField(
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                        onTaskEdited(index, newText)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(text = text, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}


@Composable
fun AddTaskFab() {
    FloatingActionButton(onClick = { /* Handle task addition */ }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTimeManagementApp() {
    TimeManagementApp()
}
