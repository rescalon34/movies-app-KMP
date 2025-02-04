package com.escalondev.movies_app_kmp.android.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.escalondev.domain.mapper.movie.toMovie
import com.escalondev.domain.model.movie.Movie
import com.escalondev.movies_app_kmp.android.R
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.ui.base.BaseScreen
import com.escalondev.movies_app_kmp.android.ui.component.BaseAppBar
import com.escalondev.movies_app_kmp.android.ui.component.BottomFadingBox
import com.escalondev.movies_app_kmp.android.ui.component.HorizontalMoviesSection
import com.escalondev.movies_app_kmp.android.ui.component.InfoMessageCard
import com.escalondev.movies_app_kmp.android.ui.component.MovieItem
import com.escalondev.movies_app_kmp.android.ui.component.SimpleProgressIndicator
import com.escalondev.movies_app_kmp.android.ui.player.YouTubePlayerBottomSheet
import com.escalondev.movies_app_kmp.android.util.Constants.FIVE_VALUE
import com.escalondev.movies_app_kmp.android.util.Constants.ZERO_VALUE
import com.escalondev.movies_app_kmp.android.util.HandleAutoScrollPagerItemAnimation
import com.escalondev.movies_app_kmp.android.util.detectOnPress
import com.escalondev.movies_app_kmp.android.util.formatDate
import com.escalondev.movies_app_kmp.data.repository.MockedMoviesRepository
import com.escalondev.movies_app_kmp.domain.util.ORIGINAL_POSTER_SIZE
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onUiEvent(HomeUiEvent.OnStart)
    }

    HomeContent(
        uiState = uiState,
        onUiEvent = viewModel::onUiEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    uiState: HomeUiState,
    onUiEvent: (HomeUiEvent) -> Unit
) {
    BaseScreen(
        modifier = Modifier.padding(bottom = 88.dp),
        topBar = { BaseAppBar(title = stringResource(R.string.home_title)) },
        screen = { paddingValues ->

            if (uiState.shouldShowPlayer) {
                YouTubePlayerBottomSheet(
                    modifier = Modifier.fillMaxHeight(),
                    video = uiState.videosByMovie.random(),
                    sheetState = rememberModalBottomSheetState(),
                    onDismiss = {
                        onUiEvent.invoke(
                            HomeUiEvent.OnChangeYouTubePlayerState(shouldShowPlayer = false)
                        )
                    }
                )
            }

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
                    uiState = uiState,
                    onUiEvent = onUiEvent
                )
            }

            if (uiState.isLoading) SimpleProgressIndicator()
        }
    )
}

@Composable
fun MainMoviesContent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onUiEvent: (HomeUiEvent) -> Unit
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        HorizontalPagerMoviesSection(uiState, onUiEvent)
        HorizontalMoviesSection(
            category = stringResource(R.string.popular),
            movies = uiState.popularMovies,
            content = { movie ->
                MovieItem(
                    movie = movie,
                    modifier = Modifier.clickable {
                        onUiEvent.invoke(HomeUiEvent.OnNavigateToMovieDetails(movie))
                    }
                )
            }
        )
        HorizontalMoviesSection(
            category = stringResource(R.string.now_playing),
            movies = uiState.nowPlayingMovies,
            content = { movie ->
                NowPlayingMovieBannerItem(
                    movie = movie,
                    onMovieClick = {
                        onUiEvent.invoke(HomeUiEvent.OnNowPlayingMovieClicked(movie))
                    }
                )
            }
        )
        HorizontalMoviesSection(
            category = stringResource(R.string.top_rated),
            movies = uiState.topRatedMovies,
            content = { movie ->
                MovieItem(
                    movie = movie,
                    modifier = Modifier
                        .clickable {
                            onUiEvent.invoke(HomeUiEvent.OnNavigateToMovieDetails(movie))
                        }
                )
            }
        )
    }
}

@Composable
private fun HorizontalPagerMoviesSection(
    uiState: HomeUiState,
    onUiEvent: (HomeUiEvent) -> Unit
) {

    val pagerState = rememberPagerState(
        initialPage = ZERO_VALUE,
        pageCount = { uiState.pagerMovies.size }
    )

    HandleAutoScrollPagerItemAnimation(pagerState, uiState.shouldAutoScroll)
    HorizontalPager(
        modifier = Modifier.height(90.dp * (FIVE_VALUE)),
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { page ->

        val pageOffset = pagerState.getOffsetDistanceInPages(page).absoluteValue

        PagerMovieItem(
            movie = uiState.pagerMovies[page],
            modifier = Modifier
                .height(90.dp * (FIVE_VALUE - pageOffset))
                .padding(horizontal = 5.dp)
                .detectOnPress { isPressing ->
                    onUiEvent(HomeUiEvent.OnChangeAutoScrollState(shouldAutoScroll = !isPressing))
                },
        )
    }
}

@Composable
private fun PagerMovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClick: () -> Unit = {}
) {
    Box {
        MovieItem(
            modifier = modifier,
            cardShape = MaterialTheme.shapes.small,
            movie = movie,
            imageSize = ORIGINAL_POSTER_SIZE
        )

        BottomFadingBox(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 5.dp)
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
    movie: Movie,
    onMovieClick: () -> Unit
) {
    MovieItem(
        modifier = modifier
            .width(280.dp)
            .height(160.dp)
            .clickable { onMovieClick() },
        cardShape = MaterialTheme.shapes.small,
        movie = movie,
        isPlayButtonVisible = true,
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeContentPreview() {
    MoviesAppTheme {
        val movies = MockedMoviesRepository.getWatchlist().map { it.toMovie() }
        MainMoviesContent(
            uiState = HomeUiState(
                pagerMovies = movies,
                popularMovies = movies,
            ),
            onUiEvent = {}
        )
    }
}
