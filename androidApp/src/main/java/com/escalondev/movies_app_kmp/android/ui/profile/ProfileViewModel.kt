package com.escalondev.movies_app_kmp.android.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escalondev.domain.usecase.profile.GetProfileUseCase
import com.escalondev.movies_app_kmp.android.navigation.navigator.AppNavigator
import com.escalondev.movies_app_kmp.android.navigation.route.ComingSoonScreenRoute
import com.escalondev.movies_app_kmp.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val navigator: AppNavigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private fun getProfile() {
        viewModelScope.launch {
            getProfileUseCase().onSuccess { data ->
                _uiState.update {
                    it.copy(
                        userName = data.username.orEmpty(),
                        name = data.name.orEmpty(),
                        imageUrl = data.imageUrl.orEmpty()
                    )
                }
            }
        }
    }

    private fun onStart() {
        getProfile()
    }

    private fun onNavigateToComingSoon(title: String) {
        viewModelScope.launch {
            navigator.navigateTo(ComingSoonScreenRoute(title))
        }
    }

    fun onUiEvent(event: ProfileUiEvent) {
        when (event) {
            is ProfileUiEvent.OnProfileOptionClick -> onNavigateToComingSoon(event.title)
            is ProfileUiEvent.OnStart -> onStart()
        }
    }
}
