package com.escalondev.movies_app_kmp.android.ui.home

import com.escalondev.domain.model.movie.Movie

sealed class HomeUiEvent {
    data object OnStart: HomeUiEvent()
    data class OnNowPlayingMovieClicked(val movie: Movie) : HomeUiEvent()
}
