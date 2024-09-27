package com.escalondev.movies_app_kmp.data.networking.expectactual

/**
 * Wrapper class representing a common error code and message Exception.
 */
internal data class PlatformSpecificException(
    val code: Int? = null,
    val message: String? = null
)

/**
 * This expect/actual declaration will delegate to each platform the collection of their specific error
 * messages whenever a custom exception happens.
 */
internal expect fun Throwable.toPlatformSpecificException(): PlatformSpecificException?
