package com.escalondev.movies_app_kmp.android.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    alertScreen: @Composable (() -> Unit)? = null,
    screen: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            topBar?.invoke()?.run { /* Default top bar */ }
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                screen()
            }
        },
        bottomBar = {
            bottomBar?.invoke()?.run { /* Default bottom bar */ }
        }
    )
}