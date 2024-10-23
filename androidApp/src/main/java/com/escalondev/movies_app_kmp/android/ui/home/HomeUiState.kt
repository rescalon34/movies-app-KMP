package com.escalondev.movies_app_kmp.android.ui.home

import com.escalondev.movies_app_kmp.domain.model.SharedMovie

data class HomeUiState(
    val pagerMovies: List<SharedMovie> = emptyList(),
    val popularMovies: List<SharedMovie> = emptyList(),
    val nowPlayingMovies: List<SharedMovie> = emptyList(),
    val topRatedMovies: List<SharedMovie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
