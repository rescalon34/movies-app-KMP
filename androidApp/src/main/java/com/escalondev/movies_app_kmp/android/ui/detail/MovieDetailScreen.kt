package com.escalondev.movies_app_kmp.android.ui.detail

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.escalondev.domain.model.movie.Movie
import com.escalondev.movies_app_kmp.android.R
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.theme.customColors
import com.escalondev.movies_app_kmp.android.ui.base.BaseScreen
import com.escalondev.movies_app_kmp.android.ui.component.BaseAppBar
import com.escalondev.movies_app_kmp.android.ui.component.BottomFadingBox
import com.escalondev.movies_app_kmp.android.ui.component.MovieItem
import com.escalondev.movies_app_kmp.android.ui.component.SquaredPrimaryButton
import com.escalondev.movies_app_kmp.android.util.formatDate
import com.escalondev.movies_app_kmp.domain.util.ORIGINAL_POSTER_SIZE

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.onUiEvent(MovieDetailUiEvent.OnStart)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MovieDetailContent(uiState, viewModel::onUiEvent)
}

@Composable
fun MovieDetailContent(
    uiState: MovieDetailUiState,
    onEvent: (MovieDetailUiEvent) -> Unit
) {

    // listen for scrolling behavior to show/hide the toolbar transparency.
    val scrollState = rememberScrollState()
    var showTransparentToolbar by remember { mutableStateOf(true) }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.value }
            .collect { scrollingValue ->
                val threshold = (scrollState.maxValue * 2) / 3
                showTransparentToolbar = scrollingValue < threshold || scrollingValue == 0
            }
    }

    BaseScreen(
        modifier = Modifier.padding(bottom = 120.dp),
        topBar = {
            DetailTopAppBar(
                title = uiState.movie.title.orEmpty(),
                isTransparentToolbar = showTransparentToolbar,
                onEvent = onEvent
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailHeader(uiState)
            DetailOverview(
                uiState = uiState,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(
    title: String,
    isTransparentToolbar: Boolean,
    onEvent: (MovieDetailUiEvent) -> Unit
) {

    val containerColor by animateColorAsState(
        if (isTransparentToolbar) Color.Transparent.copy(alpha = 0.5f) else Color.Transparent,
        label = ""
    )

    BaseAppBar(
        title = if (isTransparentToolbar) "" else title,
        isTransparent = isTransparentToolbar,
        showNavigationIcon = true,
        showNavigationContainerColor = isTransparentToolbar,
        onBackButtonClick = { onEvent(MovieDetailUiEvent.OnNavigateBack) },
        actions = {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(CircleShape)
                    .background(containerColor)
                    .padding(all = 8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    imageVector = Icons.Rounded.Share,
                    tint = MaterialTheme.customColors.white,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun DetailHeader(
    uiState: MovieDetailUiState,
) {
    Box {
        MovieItem(
            modifier = Modifier.height(520.dp),
            cardShape = MaterialTheme.shapes.extraSmall,
            imageSize = ORIGINAL_POSTER_SIZE,
            movie = uiState.movie,
        )

        BottomFadingBox(modifier = Modifier.align(Alignment.BottomStart)) {
            Column(
                Modifier
                    .padding(horizontal = 24.dp)
                    .align(Alignment.BottomStart),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.movie.title.orEmpty(),
                    style = MaterialTheme.typography.headlineLarge
                        .copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                )

                uiState.movie.releaseDate?.let { date ->
                    if (date.isNotBlank()) {
                        val releaseOn =
                            "${stringResource(R.string.movie_details)} ${date.formatDate()}"
                        Text(
                            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                            text = releaseOn,
                            style = MaterialTheme.typography.titleSmall
                                .copy(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontWeight = FontWeight.Bold
                                ),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailOverview(
    modifier: Modifier = Modifier,
    uiState: MovieDetailUiState
) {
    Column(modifier = modifier) {
        SquaredPrimaryButton(
            text = "PLAY",
            iconVector = Icons.Rounded.PlayArrow,
            shape = MaterialTheme.shapes.small,
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceTint,
                contentColor = MaterialTheme.colorScheme.scrim
            )
        ) {
            // TODO, handle play button.
        }
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = uiState.movie.overview.orEmpty(),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = uiState.movie.overview.orEmpty(),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun MovieDetailContentPreview() {
    MoviesAppTheme {
        BaseScreen {
            MovieDetailContent(
                uiState = MovieDetailUiState(
                    movie = Movie(
                        id = 1,
                        title = "Vaiana 2",
                        imageUrl = "",
                        releaseDate = "2024-11-21R",
                        overview = "After receiving an unexpected call from her wayfinding ancestors, Moana journeys alongside Maui and a new crew to the far seas of Oceania and into dangerous, long-lost waters for an adventure unlike anything she's ever faced."
                    )
                ),
                onEvent = {}
            )
        }
    }
}
