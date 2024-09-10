package com.escalondev.movies_app_kmp.android.navigation.navigator

import kotlinx.coroutines.flow.SharedFlow

interface AppNavigator {
    val navigationFlow: SharedFlow<NavigationIntent>
    suspend fun navigateTo(destination: NavigationDestination)
    suspend fun navigateToBottomNavItem(destination: NavigationDestination)
}
