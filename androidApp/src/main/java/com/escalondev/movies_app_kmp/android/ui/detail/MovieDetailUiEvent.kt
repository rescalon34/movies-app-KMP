package com.escalondev.movies_app_kmp.android.ui.detail

sealed class MovieDetailUiEvent {
    data object OnStart : MovieDetailUiEvent()
    data object OnNavigateBack : MovieDetailUiEvent()
    data class OnShareMovieDetails(val onShareMovieCallback: (String) -> Unit) : MovieDetailUiEvent()
    data class OnChangeYouTubePlayerState(val shouldShowPlayer: Boolean) : MovieDetailUiEvent()
}
