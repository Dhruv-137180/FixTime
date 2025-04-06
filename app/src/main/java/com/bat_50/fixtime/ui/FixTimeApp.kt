package com.bat_50.fixtime.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.bat_50.fixtime.ui.components.BottomNavBar
import com.bat_50.fixtime.ui.components.TopBar
import com.bat_50.fixtime.ui.navigation.NavGraph


@Composable
fun FixTimeApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavBar(navController = navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NavGraph(navController = navController) // <- this must be here!
            }
        }
    )
}