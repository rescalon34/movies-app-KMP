package com.escalondev.movies_app_kmp.android.ui.home

import com.escalondev.domain.model.Video
import com.escalondev.domain.model.movie.Movie

data class HomeUiState(
    val pagerMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val nowPlayingMovies: List<Movie> = emptyList(),
    val topRatedMovies: List<Movie> = emptyList(),
    val videosByMovie: List<Video> = emptyList(),
    val isLoading: Boolean = false,
    val shouldShowPlayer: Boolean = false,
    val shouldAutoScroll: Boolean = true,
    val errorMessage: String? = null
)
