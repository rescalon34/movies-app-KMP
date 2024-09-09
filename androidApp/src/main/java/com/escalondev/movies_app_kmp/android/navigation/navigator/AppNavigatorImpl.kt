package com.escalondev.movies_app_kmp.android.navigation.navigator

import com.escalondev.movies_app_kmp.android.navigation.route.HomeScreenRoute
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

/**
 * Helper Navigator class to manage navigation destination using SharedFlows.
 */
class AppNavigatorImpl @Inject constructor() : AppNavigator {
    private val _navigationFlow = MutableSharedFlow<NavigationIntent>()
    override val navigationFlow = _navigationFlow.asSharedFlow()

    override suspend fun navigateTo(destination: NavigationDestination) {
        _navigationFlow.emit(
            NavigationIntent.NavigateTo(
                destination = destination
            )
        )
    }

    override suspend fun navigateToBottomNavItem(destination: NavigationDestination) {
        _navigationFlow.emit(
            NavigationIntent.NavigateToBottomNavItem(
                destination = destination,
                popUpToDestination = HomeScreenRoute,
                isSingleTop = true,
                saveState = true,
            )
        )
    }
}
