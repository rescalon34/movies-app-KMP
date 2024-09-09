package com.escalondev.movies_app_kmp.android.navigation.extensions

import androidx.compose.ui.graphics.vector.ImageVector
import com.escalondev.movies_app_kmp.android.navigation.navigator.NavigationDestination

/**
 * Data class object to store all the Bottom Navigation options for each menu item.
 */
data class BottomNavItem(
    val route: NavigationDestination,
    val icon: ImageVector,
    val onBottomNavAction: (String) -> Unit = {},
)
