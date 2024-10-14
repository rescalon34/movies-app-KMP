package com.escalondev.movies_app_kmp.data.util

/**
 * Construct movies endpoint based on the category
 */
internal fun String.getMovieEndpointByCategory(): String {
    return "${MOVIE_ENDPOINT}$this"
}

internal const val MOVIE_ENDPOINT = "movie/"