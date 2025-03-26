package com.escalondev.movies_app_kmp.android.navigation.navigator

sealed class NavigationIntent {

    data object NavigateBack: NavigationIntent()

    /**
     * Navigation event Intent with custom parameters to navigate accordingly to the desired screen.
     *
     * @param destination the next screen to be shown.
     * @param popUpToDestination the previous destination to return to.
     * @param saveState Save the composable state when navigating away.
     * @param isSingleTop if set to true, it will avoid having multiple instances of the same route destination.
     */
    data class NavigateTo(
        val destination: NavigationDestination,
        val popUpToDestination: NavigationDestination? = null,
        val saveState: Boolean = false,
        val isSingleTop: Boolean = true,
    ) : NavigationIntent()

    /**
     * Navigation event Intent to navigate between bottom navigation items.
     *
     * @param destination the bottom screen to be selected.
     * @param popUpToDestination the previous destination to return to.
     * @param saveState Save the composable state when navigating away.
     * @param isSingleTop if set to true, it will avoid having multiple instances of the same route destination.
     */
    data class NavigateToBottomNavItem(
        val destination: NavigationDestination,
        val popUpToDestination: NavigationDestination? = null,
        val saveState: Boolean = false,
        val isSingleTop: Boolean = true
    ) : NavigationIntent()
}
