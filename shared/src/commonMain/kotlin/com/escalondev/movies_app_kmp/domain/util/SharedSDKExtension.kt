package com.escalondev.movies_app_kmp.domain.util

import com.escalondev.movies_app_kmp.data.util.BASE_IMAGE_URL
import com.escalondev.movies_app_kmp.data.util.DEFAULT_POSTER_SIZE

fun String.mapPosterPath(
    imageSize: String = DEFAULT_POSTER_SIZE
): String = "$BASE_IMAGE_URL$imageSize$this"
