package com.escalondev.movies_app_kmp.android.navigation.util

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

/**
 * Use this function when you don't know exactly the class to be passed as param
 * and instead use reflection to get the class name from the provided argument.
 * E.g. typeMap<KType> = customParcelableNavType(parcelableClass).
 *
 * NavType implementation for a Parcelable object. This class allows you to work with
 * Parcelable objects as navigation arguments in Jetpack Navigation.
 *
 * @param T The type of the Parcelable object.
 * @param isNullableAllowed Whether null values are allowed for this navigation argument.
 *
 * @see androidx.navigation.NavType
 */
@OptIn(InternalSerializationApi::class)
inline fun <reified T : Parcelable> customParcelableNavType(
    className: Class<T>,
    isNullableAllowed: Boolean = true
): NavType<T?> {

    return object : NavType<T?>(isNullableAllowed = isNullableAllowed) {

        private val serializer = className.kotlin.serializer()

        override fun get(bundle: Bundle, key: String): T? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(key, className)
            } else {
                @Suppress("DEPRECATION")
                bundle.getParcelable(key)
            }

        override fun parseValue(value: String): T = Json.decodeFromString(serializer, value)

        override fun serializeAsValue(value: T?): String {
            return if (value == null) "" else Json.encodeToString(serializer, value)
        }

        override fun put(bundle: Bundle, key: String, value: T?) = bundle.putParcelable(key, value)
    }
}

/**
 * Use this function when you don't know exactly the class to be passed as param
 * and instead use reflection to get the class name from the provided List<argument>.
 * E.g. typeMap<KType> = customParcelableListNavType(parcelableClass).
 *
 * NavType implementation for a Parcelable object. This class allows you to work with
 * Parcelable objects as navigation arguments in Jetpack Navigation.
 *
 * @param T The type of the Parcelable object.
 * @param isNullableAllowed Whether null values are allowed for this navigation argument.
 *
 * @see androidx.navigation.NavType
 */
@OptIn(InternalSerializationApi::class)
inline fun <reified T : Parcelable> customParcelableListNavType(
    className: Class<T>,
    isNullableAllowed: Boolean = true
): NavType<List<T>?> {
    return object : NavType<List<T>?>(isNullableAllowed = isNullableAllowed) {

        private val listSerializer = ListSerializer(className.kotlin.serializer())

        override fun get(bundle: Bundle, key: String): List<T>? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelableArrayList(key, className)
            } else {
                @Suppress("DEPRECATION")
                bundle.getParcelableArrayList(key)
            }

        override fun parseValue(value: String) = Json.decodeFromString(listSerializer, value)


        override fun serializeAsValue(value: List<T>?): String {
            return if (value == null) "" else Json.encodeToString(listSerializer, value)
        }

        override fun put(bundle: Bundle, key: String, value: List<T>?) =
            bundle.putParcelableArrayList(key, value?.let { ArrayList(it) })
    }
}
