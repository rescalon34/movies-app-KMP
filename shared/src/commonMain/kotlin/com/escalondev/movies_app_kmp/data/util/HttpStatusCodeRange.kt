package com.escalondev.movies_app_kmp.data.util

/**
 * Enum class representing standard HTTP status code ranges.
 * Each range corresponds to a category of responses as defined by the HTTP standard.
 */
internal enum class HttpStatusCodeRange(val range: IntRange) {
    SUCCESS(200..299),
    CLIENT_ERROR(400..499),
    SERVER_ERROR(500..599)
}
