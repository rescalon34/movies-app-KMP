package com.escalondev.movies_app_kmp.domain.model

/**
 * This and any other `Shared` object should not be used arbitrary
 * on the consumer side, consider creating dedicated domain objects
 * where the `Shared` object will be mapped to, so that they can be
 * used safely on the consumer side UI.
 */
data class SharedMovie(
    val id: Int,
    val title: String?,
    val imageUrl: String?,
    val releaseDate: String? = null,
)
