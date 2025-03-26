package com.escalondev.movies_app_kmp.android.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escalondev.movies_app_kmp.android.navigation.navigator.AppNavigator
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var appNavigator: AppNavigator

    fun onNavigateBack() {
        viewModelScope.launch {
            appNavigator.navigateBack()
        }
    }
}
