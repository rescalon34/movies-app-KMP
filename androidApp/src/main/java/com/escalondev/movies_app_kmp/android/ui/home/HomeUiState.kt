package com.escalondev.movies_app_kmp.android.ui.home

import com.escalondev.movies_app_kmp.domain.model.Movie

data class HomeUiState(
    val pagerMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = ""
)
