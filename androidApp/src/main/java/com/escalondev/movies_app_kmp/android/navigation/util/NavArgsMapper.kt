package com.escalondev.movies_app_kmp.android.navigation.util

import androidx.lifecycle.SavedStateHandle
import com.escalondev.movies_app_kmp.android.navigation.navigator.NavigationDestination
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 * Generic function to retrieve properties of a given Navigation Route class (NavigationDestination)
 * from a "SavedStateHandle" class using reflection.
 *
 * @param T the navigation screen where each param will be obtained using reflection.
 * @return T the "Navigation Screen" where parameters will be collected from.
 *
 * Example to get params from a composable screen.
 * ```
 * private val args = savedStateHandle.toRouteArgs<MovieDetailScreenRoute>()
 * Log.d(TAG, "movie: ${args?.movie}")
 * ```
 */
inline fun <reified T : NavigationDestination> SavedStateHandle.toRouteArgs(): T? {
    val kClass: KClass<T> = T::class
    val constructor = kClass.primaryConstructor ?: return null

    // Map each constructor parameter value by its name.
    val params = constructor.parameters.associateWith { param ->
        param.name?.let { this.get<Any?>(it) }
    }

    // Create an instance of the Data Class (T) using the retrieved parameters.
    return constructor.callBy(params)
}
