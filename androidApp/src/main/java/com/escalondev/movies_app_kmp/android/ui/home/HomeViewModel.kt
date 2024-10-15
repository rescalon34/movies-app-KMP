package com.escalondev.movies_app_kmp.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escalondev.movies_app_kmp.android.util.MovieFilter
import com.escalondev.movies_app_kmp.android.util.getCurrentLanguageCode
import com.escalondev.movies_app_kmp.core.manager.SharedCoreManager
import com.escalondev.movies_app_kmp.domain.util.onFailure
import com.escalondev.movies_app_kmp.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
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
        getMovies()
    }

    private fun getMovies() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            sharedCoreManager.useCaseProvider.getMovies(
                category = MovieFilter.TOP_RATED.value,
                page = 1,
                language = getCurrentLanguageCode()
            ).onSuccess { movies ->
                _uiState.update {
                    it.copy(isLoading = false, pagerMovies = movies)
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = error?.statusMessage)
                }
            }
        }
    }
}
