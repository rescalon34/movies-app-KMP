package com.escalondev.movies_app_kmp.android.navigation.util

import android.os.Parcelable
import androidx.navigation.NavType
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.memberProperties

/**
 * Creates a type map for the given class by extracting its `Parcelable` properties using reflection.
 *
 * @param T The class to analyze for `Parcelable` properties.
 * @return A map of property types to their corresponding `NavType`.
 *
 * Usage:
 * ```
 * composable<MovieDetailScreenRoute>(
 *      typeMap = typeMapOf<MovieDetailScreenRoute>() // using reflection approach.
 *      typeMap = mapOf(typeOf<Movie>() to customParcelableNavType(Movie::class.java)) // Non-reflection one.
 * ) {
 *      MovieDetailScreen()
 * }
 * ```
 *
 */
inline fun <reified T : Any> typeMapOf(): Map<KType, NavType<*>> {
    val kClass = T::class
    return createNavTypeMap(kClass)
}


/**
 * Generates a mapping of property types to their corresponding `NavType` for a given
 * `Serializable Destination Route` class. It iterates through the class properties,
 * identifying `Parcelable` fields and lists of `Parcelable` objects, then maps them
 * to the appropriate `NavType`.
 *
 * The resulting map is used in the `typeMapOf()` function for navigation serialization.
 */
@Suppress("UNCHECKED_CAST")
fun <T : Any> createNavTypeMap(kClass: KClass<T>): Map<KType, NavType<*>> {
    val typeMap = mutableMapOf<KType, NavType<*>>()

    kClass.memberProperties.forEach { property ->
        val returnType = property.returnType
        val classifier = returnType.classifier

        if (classifier is KClass<*> && Parcelable::class.java.isAssignableFrom(classifier.java)) {
            val parcelableClass = classifier.java as Class<Parcelable>
            typeMap[returnType] = customParcelableNavType(parcelableClass)
        } else if (classifier == List::class) {
            val argumentType = returnType.arguments.first().type?.classifier
            if (argumentType is KClass<*> && Parcelable::class.java.isAssignableFrom(argumentType.java)) {
                val parcelableClass = argumentType.java as Class<Parcelable>
                typeMap[returnType] = customParcelableListNavType(parcelableClass)
            }
        }
    }
    return typeMap
}
