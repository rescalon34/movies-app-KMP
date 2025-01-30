package com.escalondev.movies_app_kmp.android.ui.comingsoon

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.escalondev.movies_app_kmp.android.navigation.navigator.AppNavigator
import com.escalondev.movies_app_kmp.android.navigation.route.ComingSoonScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComingSoonViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

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

    private fun navigateBack() {
        viewModelScope.launch {
            appNavigator.navigateBack()
        }
    }

    fun onEvent(event: ComingSoonUiEvent) {
        when (event) {
            is ComingSoonUiEvent.OnNavigateBack -> navigateBack()
        }
    }
}
