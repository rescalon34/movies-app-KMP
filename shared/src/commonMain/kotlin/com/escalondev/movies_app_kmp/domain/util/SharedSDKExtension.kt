package com.escalondev.movies_app_kmp.domain.util

import com.escalondev.movies_app_kmp.data.networking.manager.NetworkingManagerImpl.Companion.BASE_IMAGE_URL

/**
 * Get the image URL with the desired size.
 *
 * @param imageSize the desired image resolution, by default it has a size of `w342`.
 */
fun String.getSizedImage(
    imageSize: String = DEFAULT_POSTER_SIZE
): String = "$BASE_IMAGE_URL$imageSize$this"

/**
 * Public constants variables.
 */
const val ORIGINAL_POSTER_SIZE = "original"
const val DEFAULT_POSTER_SIZE = "w342"
