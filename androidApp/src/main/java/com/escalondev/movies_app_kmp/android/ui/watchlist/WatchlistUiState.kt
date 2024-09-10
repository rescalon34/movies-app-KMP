package com.escalondev.movies_app_kmp.android.ui.watchlist

import com.escalondev.movies_app_kmp.domain.model.Movie

data class WatchlistUiState(
    val isLoading: Boolean = false,
    val watchlist: List<Movie> = emptyList()
)
