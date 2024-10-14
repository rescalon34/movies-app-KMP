package com.escalondev.movies_app_kmp.android.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escalondev.movies_app_kmp.android.util.getCurrentLanguageCode
import com.escalondev.movies_app_kmp.core.manager.SharedCoreManager
import com.escalondev.movies_app_kmp.domain.util.onFailure
import com.escalondev.movies_app_kmp.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sharedCoreManager: SharedCoreManager
) : ViewModel() {

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            sharedCoreManager.useCaseProvider.getMovies(
                category = "now_playing",
                page = 1,
                language = getCurrentLanguageCode()
            ).onSuccess { movies ->
                Log.d("tellMovies", "movies: $movies")
            }.onFailure { error ->
                Log.d("tellMovies", "error: ${error?.statusMessage}")
            }
        }
    }
}
