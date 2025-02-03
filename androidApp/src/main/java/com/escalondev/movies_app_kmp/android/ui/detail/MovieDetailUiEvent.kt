package com.escalondev.movies_app_kmp.android.ui.detail

sealed class MovieDetailUiEvent {
    data object OnStart : MovieDetailUiEvent()
}
