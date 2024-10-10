package com.escalondev.movies_app_kmp.android.ui.watchlist

sealed class WatchlistUiEvent {
    data class OnSelectOption(val showSelectOptionScreen: Boolean) : WatchlistUiEvent()
    data class OnOptionSelected(val selectedOption: String, val language: String) : WatchlistUiEvent()

    data object OnFetchWatchlist : WatchlistUiEvent()
}
