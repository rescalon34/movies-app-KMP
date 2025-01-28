package com.escalondev.movies_app_kmp.android.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escalondev.domain.usecase.home.GetMoviesUseCase
import com.escalondev.domain.usecase.home.GetVideosByMovieUseCase
import com.escalondev.movies_app_kmp.android.util.Constants.ONE_VALUE
import com.escalondev.movies_app_kmp.android.util.MovieFilter
import com.escalondev.movies_app_kmp.android.util.getCurrentLanguageCode
import com.escalondev.movies_app_kmp.domain.util.onFailure
import com.escalondev.movies_app_kmp.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getVideosByMovieUseCase: GetVideosByMovieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private fun getMoviesData() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val getPagedMoviesAsync = async { getPagerMovies() }

            val getPopularMoviesAsync = async {
                getMoviesByCategory(MovieFilter.POPULAR.value)
            }
            val getNowPlayingMoviesAsync = async {
                getMoviesByCategory(MovieFilter.NOW_PLAYING.value)
            }
            val getTopRatedMoviesAsync = async {
                getMoviesByCategory(MovieFilter.TOP_RATED.value)
            }

            awaitAll(
                getPagedMoviesAsync,
                getPopularMoviesAsync,
                getNowPlayingMoviesAsync,
                getTopRatedMoviesAsync
            )
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun getVideosByMovie(movieId: Int) {
        viewModelScope.launch {
            getVideosByMovieUseCase(movieId)
                .onSuccess { videos ->
                    // TODO: Handle this
                    Log.d("tellVideos", "videos by movie: $videos")
                }
                .onFailure { error ->
                    // TODO: Handle this
                    Log.d("tellVideos", "no videos: ${error?.statusMessage}")
                }
        }
    }

    private suspend fun getPagerMovies() {
        getMoviesUseCase(
            category = MovieFilter.UPCOMING.value,
            page = ONE_VALUE,
            language = getCurrentLanguageCode()
        ).onSuccess { movies ->
            _uiState.update { it.copy(pagerMovies = movies) }
        }.onFailure { error ->
            _uiState.update { it.copy(errorMessage = error?.statusMessage) }
        }
    }

    private suspend fun getMoviesByCategory(
        category: String
    ) {
        getMoviesUseCase(
            category = category,
            page = ONE_VALUE,
            language = getCurrentLanguageCode()
        ).onSuccess { movies ->
            _uiState.update {
                when (category) {
                    MovieFilter.POPULAR.value -> it.copy(popularMovies = movies)
                    MovieFilter.NOW_PLAYING.value -> it.copy(nowPlayingMovies = movies)
                    MovieFilter.TOP_RATED.value -> it.copy(topRatedMovies = movies)
                    else -> HomeUiState()
                }
            }
        }.onFailure { error ->
            _uiState.update { it.copy(errorMessage = error?.statusMessage) }
        }
    }

    fun onUiEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnStart -> getMoviesData()
            is HomeUiEvent.OnNowPlayingMovieClicked -> getVideosByMovie(event.movie.id)
        }
    }
}
