package com.bat_50.fixtime.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bat_50.fixtime.ui.pomodoro.PomodoroScreen
import com.bat_50.fixtime.ui.task.TaskScreen
import com.bat_50.fixtime.ui.profile.ProfileScreen
import com.bat_50.fixtime.ui.navigation.Screen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Pomodoro.route) {
        composable(Screen.Pomodoro.route) {
            PomodoroScreen(viewModel = hiltViewModel()) // 👈 preserves viewModel across tabs
        }
        composable(Screen.Task.route) {
            TaskScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}