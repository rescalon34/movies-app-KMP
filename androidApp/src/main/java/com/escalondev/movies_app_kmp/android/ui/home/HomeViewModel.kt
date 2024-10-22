package com.escalondev.movies_app_kmp.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escalondev.movies_app_kmp.android.util.Constants.ONE_VALUE
import com.escalondev.movies_app_kmp.android.util.MovieFilter
import com.escalondev.movies_app_kmp.android.util.getCurrentLanguageCode
import com.escalondev.movies_app_kmp.core.manager.SharedCoreManager
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
    private val sharedCoreManager: SharedCoreManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getMoviesData()
    }

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

    private suspend fun getPagerMovies() {
        sharedCoreManager.useCaseProvider.getMovies(
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
        sharedCoreManager.useCaseProvider.getMovies(
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
}
