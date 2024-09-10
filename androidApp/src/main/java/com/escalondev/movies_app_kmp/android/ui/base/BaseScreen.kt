package com.escalondev.movies_app_kmp.android.ui.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    alertScreen: @Composable (() -> Unit)? = null,
    screen: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier.then(modifier),
        topBar = { topBar?.let { it() } },
        content = { paddingValues ->
            screen(paddingValues)
        },
        bottomBar = {
            bottomBar?.let { it() }
        }
    )
}
