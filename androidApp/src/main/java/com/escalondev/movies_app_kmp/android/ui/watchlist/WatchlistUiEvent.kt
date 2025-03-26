package com.escalondev.movies_app_kmp.android.ui.watchlist

import com.escalondev.domain.model.movie.Movie

sealed class WatchlistUiEvent {
    data class OnSelectOption(val showSelectOptionScreen: Boolean) : WatchlistUiEvent()
    data class OnOptionSelected(val selectedOption: String) : WatchlistUiEvent()
    data object OnFetchWatchlist : WatchlistUiEvent()
    data class OnNavigateToMovieDetails(val movie: Movie): WatchlistUiEvent()
    data object OnNavigateBack : WatchlistUiEvent()
}
