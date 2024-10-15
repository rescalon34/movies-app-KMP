package com.escalondev.movies_app_kmp.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.ui.base.BaseScreen
import com.escalondev.movies_app_kmp.android.ui.component.BaseAppBar
import com.escalondev.movies_app_kmp.android.ui.component.MovieItem
import com.escalondev.movies_app_kmp.android.ui.component.SimpleProgressIndicator
import com.escalondev.movies_app_kmp.android.util.Constants.AUTO_SCROLLING_TIMER
import com.escalondev.movies_app_kmp.android.util.Constants.FIVE_VALUE
import com.escalondev.movies_app_kmp.android.util.Constants.ONE_SECOND
import com.escalondev.movies_app_kmp.android.util.Constants.ONE_VALUE
import com.escalondev.movies_app_kmp.android.util.Constants.ZERO_VALUE
import com.escalondev.movies_app_kmp.android.util.addBottomBackgroundBrush
import com.escalondev.movies_app_kmp.android.util.detectOnPress
import com.escalondev.movies_app_kmp.android.util.formatDate
import com.escalondev.movies_app_kmp.data.repository.MockedMoviesRepository
import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.util.ORIGINAL_POSTER_SIZE
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    uiState: HomeUiState
) {
    BaseScreen(
        topBar = {
            BaseAppBar(title = "Home")
        },
        screen = { paddingValues ->
            Column(Modifier.padding(paddingValues)) {
                if (uiState.isLoading) SimpleProgressIndicator()
                HorizontalPagerMovies(
                    pagerItems = uiState.pagerMovies
                )
            }
        }
    )
}

@Composable
private fun HorizontalPagerMovies(
    pagerItems: List<Movie>
) {
    var shouldAutoScroll by remember { mutableStateOf(true) }
    val pagerState = rememberPagerState(
        initialPage = ZERO_VALUE,
        pageCount = { pagerItems.size }
    )

    HandleAutoScrollPagerItemAnimation(pagerState, shouldAutoScroll)

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { page ->

        val pageOffset = pagerState.getOffsetDistanceInPages(page).absoluteValue
        PagerItem(
            movie = pagerItems[page],
            modifier = Modifier
                .height(90.dp * (FIVE_VALUE - pageOffset))
                .padding(horizontal = 5.dp)
                .detectOnPress { isPressing ->
                    shouldAutoScroll = !isPressing
                },
        )
    }
}

@Composable
private fun PagerItem(movie: Movie, modifier: Modifier = Modifier) {
    Box {
        MovieItem(
            modifier = modifier,
            cardShape = MaterialTheme.shapes.small,
            movie = movie,
            imageSize = ORIGINAL_POSTER_SIZE
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 5.dp)
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    brush = addBottomBackgroundBrush(),
                    shape = MaterialTheme.shapes.small
                )
        ) {
            movie.releaseDate?.formatDate()?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
fun HandleAutoScrollPagerItemAnimation(
    pagerState: PagerState,
    shouldAutoScroll: Boolean,
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(shouldAutoScroll) {
        delay(ONE_SECOND)
        if (pagerState.pageCount > ZERO_VALUE) {
            while (shouldAutoScroll) {
                delay(AUTO_SCROLLING_TIMER)
                coroutineScope.launch {
                    // scroll pager one by one, and loops back to the first item
                    // when reaching the last one, enabling infinite scrolling.
                    pagerState.animateScrollToPage(
                        page = (pagerState.currentPage + ONE_VALUE) % pagerState.pageCount
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeContentPreview() {
    MoviesAppTheme {
        HomeContent(
            uiState = HomeUiState(
                pagerMovies = MockedMoviesRepository.getWatchlist()
            )
        )
    }
}
