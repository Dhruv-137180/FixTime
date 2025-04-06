package com.bat_50.fixtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import com.bat_50.fixtime.theme.FixTimeTheme
import com.bat_50.fixtime.ui.FixTimeApp
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.Modifier

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FixTimeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FixTimeApp() // THIS MUST BE HERE
                }
            }
        }
    }
}
