package com.escalondev.movies_app_kmp.android.ui.watchlist

sealed class WatchlistUiEvent {
    data class OnCategorySelected(val category: String) : WatchlistUiEvent()
    data object OnFetchWatchlist : WatchlistUiEvent()
}
