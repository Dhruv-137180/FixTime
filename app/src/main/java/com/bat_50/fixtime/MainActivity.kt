package com.bat_50.fixtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bat_50.fixtime.ui.theme.FixTimeTheme
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.ReorderableLazyListState
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

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

@OptIn(ExperimentalMaterial3Api::class)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    var taskList by remember { mutableStateOf((1..5).map { "Task $it" }) }

    // Reorderable state
    val reorderState = rememberReorderableLazyListState(
        onMove = { from, to ->
            taskList = taskList.toMutableList().apply {
                add(to.index, removeAt(from.index)) // Correctly move the item
            }
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6)) // Soft background color
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello, User!",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF4A90E2) // Heading color
        )
        Spacer(modifier = Modifier.height(8.dp))
        CurrentTimeDisplay()
        Spacer(modifier = Modifier.height(16.dp))
        TaskList(taskList, reorderState)
    }
}

@Composable
fun CurrentTimeDisplay() {
    val currentTime = remember {
        java.text.SimpleDateFormat("hh:mm a", java.util.Locale.getDefault()).format(java.util.Date())
    }
    Text(text = "Current Time: $currentTime", fontSize = 18.sp, style = MaterialTheme.typography.bodyLarge)
}
@Composable
fun TaskList(taskList: List<String>, reorderState: ReorderableLazyListState) {
    // Ensure the reorderable state is properly applied to the LazyColumn
    LazyColumn(state = reorderState.listState, modifier = Modifier
        .fillMaxSize()
        .reorderable(reorderState)) {
        itemsIndexed(taskList, key = { _, item -> item }) { index, item ->
            // Wrapping each item with ReorderableItem to allow dragging
            ReorderableItem(reorderState, key = item) { isDragging ->
                TaskItem(taskName = item, isDragging = isDragging)
            }
        }
    }
}

@Composable
fun TaskItem(taskName: String, isDragging: Boolean) {
    // Item card appearance when dragging or idle
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) // Increased padding for better spacing
            .background(if (isDragging) Color(0xFFE1E8ED) else Color.White), // Highlight color while dragging
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicText(text = taskName, style = MaterialTheme.typography.bodyLarge)
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
