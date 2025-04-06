package com.bat_50.fixtime.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timer
import androidx.compose.ui.graphics.vector.ImageVector
import com.bat_50.fixtime.ui.navigation.Screen

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Tasks : BottomNavItem(
        route = Screen.Task.route,
        icon = Icons.Default.CheckCircle,
        label = "Tasks"
    )

    object Pomodoro : BottomNavItem(
        route = Screen.Pomodoro.route,
        icon = Icons.Default.Timer,
        label = "Pomodoro"
    )

    object Profile : BottomNavItem(
        route = Screen.Profile.route,
        icon = Icons.Default.Person,
        label = "Profile"
    )
}