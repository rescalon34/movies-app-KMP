package com.escalondev.domain.mapper.movie

import com.escalondev.domain.model.movie.Movie
import com.escalondev.movies_app_kmp.domain.model.SharedMovie

fun SharedMovie.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        imageUrl = imageUrl,
        releaseDate = releaseDate,
    )
}
