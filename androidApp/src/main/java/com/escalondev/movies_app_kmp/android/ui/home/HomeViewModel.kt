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
import kotlinx.coroutines.delay
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
            val getPopularMoviesAsync = async { getPopularMovies() }

            awaitAll(getPagedMoviesAsync, getPopularMoviesAsync)
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private suspend fun getPagerMovies() {
        sharedCoreManager.useCaseProvider.getMovies(
            category = MovieFilter.TOP_RATED.value,
            page = ONE_VALUE,
            language = getCurrentLanguageCode()
        ).onSuccess { movies ->
            _uiState.update { it.copy(pagerMovies = movies) }
        }.onFailure { error ->
            _uiState.update { it.copy(errorMessage = error?.statusMessage) }
        }
    }

    private suspend fun getPopularMovies() {
        sharedCoreManager.useCaseProvider.getMovies(
            category = MovieFilter.POPULAR.value,
            page = ONE_VALUE,
            language = getCurrentLanguageCode()
        ).onSuccess { movies -> _uiState.update { it.copy(popularMovies = movies) } }
    }
}
