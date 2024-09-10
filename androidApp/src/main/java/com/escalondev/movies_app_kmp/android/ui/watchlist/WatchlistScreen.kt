package com.escalondev.movies_app_kmp.android.ui.watchlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.escalondev.movies_app_kmp.android.R
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.ui.base.BaseScreen
import com.escalondev.movies_app_kmp.android.ui.component.BaseAppBar
import com.escalondev.movies_app_kmp.android.ui.component.CategoryCard
import com.escalondev.movies_app_kmp.android.ui.component.MovieItem
import com.escalondev.movies_app_kmp.android.ui.component.SimpleProgressIndicator
import com.escalondev.movies_app_kmp.data.repository.MovieRepositoryImpl

@Composable
fun WatchlistScreen(
    viewModel: WatchlistViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.onUiEvent(WatchlistUiEvent.OnFetchWatchlist)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WatchlistContent(
        uiState = uiState,
        onUiEvent = viewModel::onUiEvent
    )
}

/**
 * Main Watchlist screen Scaffold content.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistContent(
    uiState: WatchlistUiState,
    onUiEvent: (event: WatchlistUiEvent) -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )

    BaseScreen(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            WatchlistTopAppBar(
                scrollBehavior = scrollBehavior,
                onUiEvent = onUiEvent
            )
        },
        screen = { paddingValues ->
            Column(Modifier.padding(paddingValues)) {
                if (uiState.isLoading) SimpleProgressIndicator()
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(160.dp),
                    contentPadding = PaddingValues(
                        top = 8.dp,
                        bottom = 88.dp,
                        start = 10.dp,
                        end = 10.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item {
                        Text(
                            text = stringResource(R.string.watchlist_subtitle),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium)
                        )
                    }
                    item { /* Empty to represent an space next to the Text() */ }
                    items(uiState.watchlist) { movie ->
                        MovieItem(
                            modifier = Modifier.height(260.dp),
                            movie = movie,
                        )
                    }
                }
            }
        }
    )
}

/**
 * Watchlist TopAppBar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onUiEvent: (event: WatchlistUiEvent) -> Unit = {}
) {
    BaseAppBar(
        title = stringResource(R.string.watchlist_title),
        scrollBehavior = scrollBehavior,
        actions = {
            CategoryCard(
                modifier = Modifier.padding(end = 6.dp),
                category = "Featured",
                onCategoryClick = { category ->
                    onUiEvent(WatchlistUiEvent.OnCategorySelected(category))
                }
            )
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun WatchlistContentPreview() {
    MoviesAppTheme {
        WatchlistContent(
            uiState = WatchlistUiState(
                watchlist = MovieRepositoryImpl.getWatchlist()
            )
        )
    }
}
