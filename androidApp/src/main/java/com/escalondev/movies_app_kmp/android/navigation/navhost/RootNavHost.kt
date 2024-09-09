package com.escalondev.movies_app_kmp.android.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.escalondev.movies_app_kmp.android.navigation.route.HomeScreenRoute
import com.escalondev.movies_app_kmp.android.navigation.route.ProfileScreenRoute
import com.escalondev.movies_app_kmp.android.navigation.route.WatchlistScreenRoute
import com.escalondev.movies_app_kmp.android.ui.home.HomeScreen
import com.escalondev.movies_app_kmp.android.ui.profile.ProfileScreen
import com.escalondev.movies_app_kmp.android.ui.watchlist.WatchlistScreen

/**
 * Root navigation host, it contains all the composable navigation screens within the app.
 */
@Composable
fun RootNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute
    ) {
        composable<HomeScreenRoute> {
            HomeScreen()
        }
        composable<WatchlistScreenRoute> {
            WatchlistScreen()
        }
        composable<ProfileScreenRoute> {
            ProfileScreen()
        }
    }
}
