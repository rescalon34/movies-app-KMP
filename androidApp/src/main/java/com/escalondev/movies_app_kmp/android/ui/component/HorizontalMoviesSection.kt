package com.escalondev.movies_app_kmp.android.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escalondev.domain.mapper.movie.toMovie
import com.escalondev.domain.model.movie.Movie
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.data.repository.MockedMoviesRepository

@Composable
fun HorizontalMoviesSection(
    modifier: Modifier = Modifier,
    category: String,
    movies: List<Movie>,
    content: @Composable (Movie) -> Unit = {}
) {
    Column(modifier = modifier.padding(top = 20.dp)) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 6.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            text = category,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(movies) { movie ->
                content(movie)
            }
        }
    }
}

@Preview
@Composable
private fun HorizontalMoviesSectionPreview() {
    MoviesAppTheme {
        HorizontalMoviesSection(
            category = "Popular",
            movies = MockedMoviesRepository.getWatchlist()
                .map { it.toMovie() }
        )
    }
}
