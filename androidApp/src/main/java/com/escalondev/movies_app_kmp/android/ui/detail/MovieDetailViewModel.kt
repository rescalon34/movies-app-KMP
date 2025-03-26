package com.escalondev.movies_app_kmp.android.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.escalondev.domain.model.Video
import com.escalondev.domain.usecase.home.GetVideosByMovieUseCase
import com.escalondev.movies_app_kmp.android.navigation.route.MovieDetailScreenRoute
import com.escalondev.movies_app_kmp.android.navigation.util.toRouteArgs
import com.escalondev.movies_app_kmp.android.ui.base.BaseViewModel
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
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVideosByMovieUseCase: GetVideosByMovieUseCase,
) : BaseViewModel() {

    // Stateless
    private val args = savedStateHandle.toRouteArgs<MovieDetailScreenRoute>()

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    private fun onStart() {
        args?.movie?.let { movie ->
            getVideosByMovie(movie.id)
        }
    }

    private fun getVideosByMovie(movieId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getVideosByMovieUseCase(movieId)
                .onSuccess { videos ->
                    displayMovieDetails(videos)
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error?.statusMessage
                        )
                    }
                }
        }
    }

    private fun displayMovieDetails(videos: List<Video>) {
        args?.movie?.let { movie ->
            _uiState.update {
                it.copy(movie = movie, isLoading = false, videosByMovie = videos)
            }
        }
    }

    private fun onShareMovieDetails(onShareMovieCallback: (String) -> Unit) {
        onShareMovieCallback(args?.movie?.title.orEmpty())
    }

    /**
     * This will determine if the YouTube Player bottom sheet should be shown or not
     * depending on the user's event triggered.
     */
    private fun onChangeYouTubePlayerState(shouldShowPlayer: Boolean) {
        _uiState.update { it.copy(shouldShowPlayer = shouldShowPlayer) }
    }

    fun onUiEvent(event: MovieDetailUiEvent) {
        when (event) {
            is MovieDetailUiEvent.OnStart -> onStart()
            MovieDetailUiEvent.OnNavigateBack -> onNavigateBack()
            is MovieDetailUiEvent.OnShareMovieDetails -> onShareMovieDetails(event.onShareMovieCallback)
            is MovieDetailUiEvent.OnChangeYouTubePlayerState -> {
                onChangeYouTubePlayerState(shouldShowPlayer = event.shouldShowPlayer)
            }
        }
    }
}
