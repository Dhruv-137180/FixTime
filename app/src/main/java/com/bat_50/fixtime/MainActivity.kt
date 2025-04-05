package com.bat_50.fixtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.bat_50.fixtime.ui.task.TaskScreen
import com.bat_50.fixtime.theme.FixTimeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FixTimeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    TaskScreen() // 👈 Hooking up the Task screen
                }
            }
        }
    }
}
