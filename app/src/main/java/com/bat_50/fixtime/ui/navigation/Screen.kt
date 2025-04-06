package com.bat_50.fixtime.ui.navigation

sealed class Screen(val route: String) {
    object Pomodoro : Screen("pomodoro")
    object Task : Screen("tasks")
    object Profile : Screen("profile")
}