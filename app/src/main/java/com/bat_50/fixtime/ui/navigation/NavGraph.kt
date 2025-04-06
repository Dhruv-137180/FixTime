package com.bat_50.fixtime.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bat_50.fixtime.ui.pomodoro.PomodoroScreen
import com.bat_50.fixtime.ui.profile.ProfileScreen
import com.bat_50.fixtime.ui.task.TaskScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Task.route
    ) {
        composable(Screen.Task.route) {
            TaskScreen()
        }
        composable(Screen.Pomodoro.route) {
            PomodoroScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}