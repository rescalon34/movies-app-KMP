package com.escalondev.movies_app_kmp.android.ui.detail

import com.escalondev.movies_app_kmp.android.ui.home.HomeUiEvent

sealed class MovieDetailUiEvent {
    data object OnStart : MovieDetailUiEvent()
    data object OnNavigateBack : MovieDetailUiEvent()
    data class OnChangeYouTubePlayerState(val shouldShowPlayer: Boolean) : MovieDetailUiEvent()
}
