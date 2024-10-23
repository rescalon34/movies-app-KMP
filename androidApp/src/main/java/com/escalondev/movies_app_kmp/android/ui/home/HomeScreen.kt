package com.escalondev.movies_app_kmp.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.escalondev.movies_app_kmp.android.ui.component.HorizontalMoviesSection
import com.escalondev.movies_app_kmp.android.ui.component.InfoMessageCard
import com.escalondev.movies_app_kmp.android.ui.component.MovieItem
import com.escalondev.movies_app_kmp.android.ui.component.SimpleProgressIndicator
import com.escalondev.movies_app_kmp.android.util.Constants.FIVE_VALUE
import com.escalondev.movies_app_kmp.android.util.Constants.ZERO_VALUE
import com.escalondev.movies_app_kmp.android.util.HandleAutoScrollPagerItemAnimation
import com.escalondev.movies_app_kmp.android.util.addBottomBackgroundBrush
import com.escalondev.movies_app_kmp.android.util.detectOnPress
import com.escalondev.movies_app_kmp.android.util.formatDate
import com.escalondev.movies_app_kmp.data.repository.MockedMoviesRepository
import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.util.ORIGINAL_POSTER_SIZE
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
fun HomeContent(uiState: HomeUiState) {
    BaseScreen(
        modifier = Modifier.padding(bottom = 88.dp),
        topBar = { BaseAppBar(title = stringResource(R.string.home_title)) },
        screen = { paddingValues ->
            if (uiState.isLoading) SimpleProgressIndicator()

            uiState.errorMessage?.let { error ->
                InfoMessageCard(
                    modifier = Modifier.padding(paddingValues),
                    title = stringResource(id = R.string.general_error_title),
                    description = error
                )
            }

            if (uiState.pagerMovies.isNotEmpty()) {
                MainMoviesContent(
                    modifier = Modifier.padding(paddingValues),
                    uiState
                )
            }
        }
    )
}

@Composable
fun MainMoviesContent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        HorizontalPagerMoviesSection(pagerItems = uiState.pagerMovies)
        HorizontalMoviesSection(
            movies = uiState.popularMovies,
            category = stringResource(R.string.popular),
            content = { MovieItem(movie = it) }
        )
        HorizontalMoviesSection(
            movies = uiState.nowPlayingMovies,
            category = stringResource(R.string.now_playing),
            content = { NowPlayingMovieBannerItem(movie = it) }
        )
        HorizontalMoviesSection(
            movies = uiState.topRatedMovies,
            category = stringResource(R.string.top_rated),
            content = { MovieItem(movie = it) }
        )
    }
}

@Composable
private fun HorizontalPagerMoviesSection(
    pagerItems: List<SharedMovie>
) {
    var shouldAutoScroll by remember { mutableStateOf(true) }
    val pagerState = rememberPagerState(
        initialPage = ZERO_VALUE,
        pageCount = { pagerItems.size }
    )

    HandleAutoScrollPagerItemAnimation(pagerState, shouldAutoScroll)

    HorizontalPager(
        modifier = Modifier.height(90.dp * (FIVE_VALUE)),
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { page ->

        val pageOffset = pagerState.getOffsetDistanceInPages(page).absoluteValue

        PagerMovieItem(
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
private fun PagerMovieItem(movie: SharedMovie, modifier: Modifier = Modifier) {
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
                    style = MaterialTheme.typography.titleSmall
                        .copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold
                        ),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
fun NowPlayingMovieBannerItem(
    modifier: Modifier = Modifier,
    movie: SharedMovie
) {
    MovieItem(
        modifier = modifier
            .width(280.dp)
            .height(160.dp),
        cardShape = MaterialTheme.shapes.small,
        movie = movie,
        isPlayButtonVisible = true
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeContentPreview() {
    MoviesAppTheme {
        MainMoviesContent(
            uiState = HomeUiState(
                pagerMovies = MockedMoviesRepository.getWatchlist(),
                popularMovies = MockedMoviesRepository.getWatchlist()
            )
        )
    }
}
