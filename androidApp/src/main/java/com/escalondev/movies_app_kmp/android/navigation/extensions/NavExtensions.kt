package com.escalondev.movies_app_kmp.android.navigation.extensions

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.escalondev.movies_app_kmp.android.navigation.navigator.AppNavigator
import com.escalondev.movies_app_kmp.android.navigation.navigator.NavigationDestination
import com.escalondev.movies_app_kmp.android.navigation.navigator.NavigationIntent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

/**
 * Composable responsible to manage all navigation events defined in [NavigationIntent]
 * @param navigationFlow the flow used in [AppNavigator] to emit all navigation events
 * @param navHostController the navHost to be used in the app
 */
@Composable
fun NavigationEffects(
    navigationFlow: SharedFlow<NavigationIntent>,
    navHostController: NavHostController,
) {

    val activity = LocalContext.current as? Activity
    LaunchedEffect(activity, navHostController, navigationFlow) {
        navigationFlow.collectLatest { navigationIntent ->
            if (activity?.isFinishing == true) {
                return@collectLatest
            }
            when (navigationIntent) {
                is NavigationIntent.NavigateTo -> {
                    navHostController.safeNavigate(navigationIntent.destination) {
                        launchSingleTop = navigationIntent.isSingleTop
                        navigationIntent.popUpToDestination?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { saveState = navigationIntent.saveState }
                        }
                    }
                }

                is NavigationIntent.NavigateToBottomNavItem -> {
                    navHostController.navigate(navigationIntent.destination) {
                        launchSingleTop = navigationIntent.isSingleTop
                        // Ensures that when you navigate back to the saved composable, its previously saved state is restored.
                        // (if false), the saved state won't be applied, and the composable may be re-created from scratch.
                        restoreState = navigationIntent.saveState

                        navigationIntent.popUpToDestination?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) {
                                // Ensures that the state of the current composable is saved when you navigate away to another destination.
                                // (if false), the composable’s state will not be preserved.
                                //inclusive = navigationIntent.saveState
                                saveState = navigationIntent.saveState
                            }
                        }
                    }
                }

                NavigationIntent.NavigateBack -> navHostController.navigateUp()
            }
        }
    }
}

/**
 * Checks if [lifecycleIsResumed] before attempting to navigate to avoid duplicated navigation
 * that can occur on multiple clicks
 */
private fun NavHostController.safeNavigate(
    route: NavigationDestination,
    builder: NavOptionsBuilder.() -> Unit
) {
    if (currentBackStackEntry?.lifecycleIsResumed() == true) {
        navigate(route) {
            builder()
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 * This is used to de-duplicate navigation events.
 *
 * See also
 * [Duplicated Nav Events](https://github.com/google/accompanist/issues/1320#issuecomment-1238539724)
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
