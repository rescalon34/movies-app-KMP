package com.escalondev.domain.mapper.movie

import com.escalondev.domain.model.Video
import com.escalondev.domain.model.movie.Movie
import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.model.SharedVideo

fun SharedVideo.toVideo(): Video {
    return Video(
        id = id,
        key = key,
        name = name
    )
}
