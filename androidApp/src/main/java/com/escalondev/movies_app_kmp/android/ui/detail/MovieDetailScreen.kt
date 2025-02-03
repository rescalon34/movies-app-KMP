package com.escalondev.movies_app_kmp.android.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.onUiEvent(MovieDetailUiEvent.OnStart)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MovieDetailContent(uiState)
}

@Composable
fun MovieDetailContent(
    uiState: MovieDetailUiState
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = uiState.movie,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MovieDetailContentPreview() {
    MoviesAppTheme {
        MovieDetailContent(
            uiState = MovieDetailUiState(
                movie = "Vaiana 2"
            )
        )
    }
}
