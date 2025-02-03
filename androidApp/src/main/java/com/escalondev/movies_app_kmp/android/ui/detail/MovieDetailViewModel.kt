package com.escalondev.movies_app_kmp.android.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.escalondev.movies_app_kmp.android.navigation.route.MovieDetailScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Stateless
    private val args = savedStateHandle.toRoute<MovieDetailScreenRoute>()

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    private fun onStart() {
        _uiState.update { it.copy(movie = args.movie) }
    }

    fun onUiEvent(event: MovieDetailUiEvent) {
        when (event) {
            is MovieDetailUiEvent.OnStart -> onStart()
        }
    }
}
