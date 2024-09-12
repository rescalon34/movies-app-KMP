package com.escalondev.movies_app_kmp.android.ui.watchlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escalondev.movies_app_kmp.android.util.Constants.ONE_SECOND
import com.escalondev.movies_app_kmp.data.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(WatchlistUiState())
    val uiState: StateFlow<WatchlistUiState> = _uiState.asStateFlow()

    private fun getWatchlist() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            delay(ONE_SECOND)
            _uiState.update {
                it.copy(isLoading = false, watchlist = MovieRepositoryImpl.getWatchlist())
            }
        }
    }

    fun onUiEvent(uiEvent: WatchlistUiEvent) {
        when (uiEvent) {
            WatchlistUiEvent.OnFetchWatchlist -> getWatchlist()
            is WatchlistUiEvent.OnOptionSelected -> {
                _uiState.update { it.copy(selectedOption = uiEvent.selectedOption) }
                Log.d("WatchlistViewModel", "TBD, call API: ${uiEvent.selectedOption}")
            }

            is WatchlistUiEvent.OnSelectOption -> {
                _uiState.update { it.copy(showSelectOptionScreen = uiEvent.showSelectOptionScreen) }
            }
        }
    }
}
