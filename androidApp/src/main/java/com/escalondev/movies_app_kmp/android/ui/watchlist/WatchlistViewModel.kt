package com.escalondev.movies_app_kmp.android.ui.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escalondev.domain.usecase.watchlist.GetWatchlistMoviesUseCase
import com.escalondev.domain.usecase.watchlist.GetWatchlistUseCase
import com.escalondev.movies_app_kmp.android.ui.filter.SortType
import com.escalondev.movies_app_kmp.android.util.Constants.ONE_SECOND
import com.escalondev.movies_app_kmp.android.util.getCurrentLanguageCode
import com.escalondev.movies_app_kmp.domain.util.onFailure
import com.escalondev.movies_app_kmp.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val getWatchlistUseCase: GetWatchlistUseCase,
    private val getWatchlistMoviesUseCase: GetWatchlistMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WatchlistUiState())
    val uiState: StateFlow<WatchlistUiState> = _uiState.asStateFlow()

    private fun getWatchlist() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            delay(ONE_SECOND)
            getWatchlistUseCase().collectLatest { watchlist ->
                _uiState.update {
                    it.copy(isLoading = false, watchlist = watchlist)
                }
            }
        }
    }

    private fun getWatchlistMovies(sortType: String = SortType.FIRST_ADDED.sortType) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getWatchlistMoviesUseCase(
                sortBy = sortType,
                language = getCurrentLanguageCode()
            ).onSuccess { watchlist ->
                _uiState.update {
                    it.copy(isLoading = false, watchlist = watchlist, errorMessage = null)
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = error?.statusMessage)
                }
            }
        }
    }

    private fun onFilterMoviesBySelectedOption(selectedOption: String) {
        val sortType = _uiState.value.options.find { it.displayName == selectedOption }?.sortType
        sortType?.let { getWatchlistMovies(sortType = it) }
    }

    fun onUiEvent(uiEvent: WatchlistUiEvent) {
        when (uiEvent) {
            is WatchlistUiEvent.OnFetchWatchlist -> getWatchlistMovies()

            is WatchlistUiEvent.OnOptionSelected -> {
                _uiState.update { it.copy(selectedOption = uiEvent.selectedOption) }
                onFilterMoviesBySelectedOption(selectedOption = uiEvent.selectedOption)
            }

            is WatchlistUiEvent.OnSelectOption -> {
                _uiState.update { it.copy(showSelectOptionScreen = uiEvent.showSelectOptionScreen) }
            }
        }
    }
}
