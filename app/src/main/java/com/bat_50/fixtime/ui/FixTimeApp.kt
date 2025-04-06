package com.bat_50.fixtime.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.bat_50.fixtime.ui.components.BottomNavBar
import com.bat_50.fixtime.ui.components.TopBar
import com.bat_50.fixtime.ui.navigation.NavGraph
import com.bat_50.fixtime.ui.navigation.Screen
import java.util.*

@Composable
fun FixTimeApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NavGraph(navController = navController)
            }
        }
    )
}