package com.escalondev.movies_app_kmp.android.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escalondev.movies_app_kmp.android.navigation.navigator.AppNavigator
import com.escalondev.movies_app_kmp.android.navigation.route.MovieDetailScreenRoute
import com.escalondev.movies_app_kmp.android.navigation.util.toRouteArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val appNavigator: AppNavigator
) : ViewModel() {

    // Stateless
    private val args = savedStateHandle.toRouteArgs<MovieDetailScreenRoute>()

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    private fun onStart() {
        args?.movie?.let { mov ->
            _uiState.update { it.copy(movie = mov) }
        }
    }

    private fun onNavigateBack() {
        viewModelScope.launch {
            appNavigator.navigateBack()
        }
    }

    fun onUiEvent(event: MovieDetailUiEvent) {
        when (event) {
            is MovieDetailUiEvent.OnStart -> onStart()
            MovieDetailUiEvent.OnNavigateBack -> onNavigateBack()
        }
    }
}
