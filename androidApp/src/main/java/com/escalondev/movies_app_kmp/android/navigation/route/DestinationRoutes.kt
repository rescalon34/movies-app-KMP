package com.escalondev.movies_app_kmp.android.navigation.route

import com.escalondev.movies_app_kmp.android.navigation.navigator.NavigationDestination
import kotlinx.serialization.Serializable

/**
 * File to store all Destination Routes.
 */
@Serializable
object HomeScreenRoute : NavigationDestination

@Serializable
data class MovieDetailScreenRoute(val movie: String) : NavigationDestination

@Serializable
object WatchlistScreenRoute : NavigationDestination

@Serializable
object ProfileScreenRoute : NavigationDestination

@Serializable
data class ComingSoonScreenRoute(val title: String) : NavigationDestination
