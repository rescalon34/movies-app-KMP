package com.escalondev.movies_app_kmp.android.ui.comingsoon

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.escalondev.movies_app_kmp.android.navigation.route.ComingSoonScreenRoute
import com.escalondev.movies_app_kmp.android.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ComingSoonViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    // Stateless
    private val args = savedStateHandle.toRoute<ComingSoonScreenRoute>()

    // Stateful
    private val _uiState = MutableStateFlow(ComingSoonUiState())
    val uiState: StateFlow<ComingSoonUiState> = _uiState.asStateFlow()

    init {
        setupTitle()
    }

    private fun setupTitle() {
        _uiState.update { it.copy(title = args.title) }
    }

    fun onEvent(event: ComingSoonUiEvent) {
        when (event) {
            is ComingSoonUiEvent.OnNavigateBack -> onNavigateBack()
        }
    }
}
