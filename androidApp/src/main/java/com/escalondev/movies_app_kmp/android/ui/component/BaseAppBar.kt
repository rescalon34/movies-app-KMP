package com.escalondev.movies_app_kmp.android.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme

/**
 * Reusable Top AppBar across different screens.
 *
 * @param modifier to apply any modification to the TopAppBar.
 * @param title the title of the AppBar
 * @param scrollBehavior the desired scrollBehavior whenever scrolling the screen content.
 * @param actions whether the AppBar must show extra action buttons to the right.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppBar(
    modifier: Modifier = Modifier,
    title: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        title = { Text(text = title) },
        actions = actions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun BaseAppBarPreview() {
    MoviesAppTheme {
        BaseAppBar(title = "Home")
    }
}
