package com.escalondev.movies_app_kmp.android.ui.profile

sealed class ProfileUiEvent {
    data object OnStart : ProfileUiEvent()
    data class OnProfileOptionClick(val title: String) : ProfileUiEvent()
}
