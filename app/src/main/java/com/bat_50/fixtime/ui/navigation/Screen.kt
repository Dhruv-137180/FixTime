package com.bat_50.fixtime.ui.navigation


sealed class Screen(val route: String) {
    object Task : Screen("task")
    // Add more screens later like:
    // object Pomodoro : Screen("pomodoro")
    // object Settings : Screen("settings")
}