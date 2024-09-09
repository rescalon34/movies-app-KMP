package com.escalondev.movies_app_kmp.android.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escalondev.movies_app_kmp.android.navigation.extensions.BottomNavItem
import com.escalondev.movies_app_kmp.android.navigation.navigator.AppNavigator
import com.escalondev.movies_app_kmp.android.navigation.navigator.NavigationDestination
import com.escalondev.movies_app_kmp.android.navigation.route.HomeScreenRoute
import com.escalondev.movies_app_kmp.android.navigation.route.ProfileScreenRoute
import com.escalondev.movies_app_kmp.android.navigation.route.WatchlistScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    val navigationFlow = appNavigator.navigationFlow

    /**
     * Create the list of bottom navigation items.
     */
    val navItems = listOf(
        BottomNavItem(
            route = HomeScreenRoute,
            icon = Icons.Filled.Home,
            onBottomNavAction = {
                switchToBottomNavItem(HomeScreenRoute)
            },
        ),
        BottomNavItem(
            route = WatchlistScreenRoute,
            icon = Icons.Filled.AddCircle,
            onBottomNavAction = {
                switchToBottomNavItem(WatchlistScreenRoute)
            },
        ),
        BottomNavItem(
            route = ProfileScreenRoute,
            icon = Icons.Filled.AccountCircle,
            onBottomNavAction = {
                switchToBottomNavItem(ProfileScreenRoute)
            },
        )
    )

    /**
     * navigate to the selected bottom navigationBar item screen.
     */
    private fun switchToBottomNavItem(destination: NavigationDestination) {
        viewModelScope.launch {
            appNavigator.navigateToBottomNavItem(destination)
        }
    }
}
