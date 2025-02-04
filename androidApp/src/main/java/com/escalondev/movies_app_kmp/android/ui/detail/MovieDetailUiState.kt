package com.escalondev.movies_app_kmp.android.ui.detail

import com.escalondev.domain.model.Video
import com.escalondev.domain.model.movie.Movie

data class MovieDetailUiState(
    val movie: Movie = Movie(),
    val videosByMovie: List<Video> = emptyList(),
    val shouldShowPlayer: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
