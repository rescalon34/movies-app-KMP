package com.escalondev.movies_app_kmp.data.networking.expectactual

import io.ktor.client.engine.darwin.DarwinHttpRequestException

/**
 * Cast any platform exceptions from the iOS ecosystem, for example: the [DarwinHttpRequestException]
 * will provide specific error message whenever the user has no internet connection.
 */
internal actual fun Throwable.toPlatformSpecificException(): PlatformSpecificException? {
    return when (this) {
        is DarwinHttpRequestException -> {
            PlatformSpecificException(
                code = origin.code.toInt(),
                message = origin.localizedDescription
            )
        }

        else -> null
    }
}
