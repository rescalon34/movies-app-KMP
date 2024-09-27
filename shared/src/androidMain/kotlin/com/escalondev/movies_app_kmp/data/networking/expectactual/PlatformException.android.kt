package com.escalondev.movies_app_kmp.data.networking.expectactual

import java.net.UnknownHostException

/**
 * Cast any platform exceptions from the Android ecosystem, for example: the [UnknownHostException]
 * will provide specific error message whenever the user has no internet connection.
 */
internal actual fun Throwable.toPlatformSpecificException(): PlatformSpecificException? {
    return when (this) {
        is UnknownHostException -> PlatformSpecificException(
            code = hashCode(),
            message = message.orEmpty()
        )

        else -> null
    }
}
