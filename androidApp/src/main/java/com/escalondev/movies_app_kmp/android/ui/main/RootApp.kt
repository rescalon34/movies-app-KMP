package com.escalondev.movies_app_kmp.android.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.escalondev.movies_app_kmp.android.navigation.extensions.BottomNavItem
import com.escalondev.movies_app_kmp.android.navigation.extensions.NavigationEffects
import com.escalondev.movies_app_kmp.android.navigation.extensions.getRouteName
import com.escalondev.movies_app_kmp.android.navigation.navhost.RootNavHost
import com.escalondev.movies_app_kmp.android.navigation.navigator.NavigationIntent
import com.escalondev.movies_app_kmp.android.navigation.route.HomeScreenRoute
import com.escalondev.movies_app_kmp.android.navigation.route.WatchlistScreenRoute
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.ui.base.BaseScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

/**
 * Main composable entry point, it will listen for navigation effects, and contains the main scaffold
 * to display the screen content and bottom nav bar whenever navigating to a screen.
 */
@Composable
fun RootApp(
    navigationFlow: SharedFlow<NavigationIntent>,
    navItems: List<BottomNavItem>,
) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.getRouteName()

    // Listens for navigation events on every screen.
    NavigationEffects(
        navigationFlow = navigationFlow,
        navHostController = navController
    )

    // Screen template to show the bottom navigation Bar on all the screens from the bottom menu.
    BaseScreen(
        bottomBar = {
            BottomNavigationBar(
                navItems = navItems,
                currentRoute = currentRoute,
            )
        }, screen = {
            RootNavHost(navController = navController)
        }
    )
}

/**
 * Map each Bottom navigation item to display it in the NavigationBar.
 */
@Composable
private fun BottomNavigationBar(
    navItems: List<BottomNavItem>,
    currentRoute: String?,
) {
    NavigationBar {
        navItems.forEachIndexed { _, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                selected = item.route.routeName == currentRoute,
                onClick = { item.onBottomNavAction(currentRoute.orEmpty()) }
            )
        }
    }
}

@Preview
@Composable
private fun RootAppPreview() {
    MoviesAppTheme {
        RootApp(
            navigationFlow = MutableSharedFlow(0),
            navItems = listOf(
                BottomNavItem(route = HomeScreenRoute, icon = Icons.Filled.Home),
                BottomNavItem(route = WatchlistScreenRoute, icon = Icons.Filled.AddCircle),
                BottomNavItem(route = HomeScreenRoute, icon = Icons.Filled.AccountCircle),
            )
        )
    }
}
