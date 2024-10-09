package com.escalondev.movies_app_kmp.android.ui.watchlist

import com.escalondev.movies_app_kmp.android.ui.filter.SortType
import com.escalondev.movies_app_kmp.domain.model.Movie

data class WatchlistUiState(
    val isLoading: Boolean = false,
    val watchlist: List<Movie> = emptyList(),
    val options: List<SortType> = SortType.entries,
    val selectedOption: String = SortType.entries.first().displayName,
    val showSelectOptionScreen: Boolean = false,
    val errorMessage: String? = null
)
