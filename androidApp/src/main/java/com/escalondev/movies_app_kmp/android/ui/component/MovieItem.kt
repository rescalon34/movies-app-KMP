package com.escalondev.movies_app_kmp.android.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.domain.model.Movie

@Composable
fun MovieItem(modifier: Modifier = Modifier, movie: Movie) {
    Card(modifier = modifier) {
        AsyncImage(
            model = movie.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun MovieItemPreview() {
    MoviesAppTheme {
        MovieItem(
            modifier = Modifier.height(170.dp),
            movie = Movie(
                0,
                "",
                "https://media.themoviedb.org/t/p/original/t7bhjraXuN4hd3yZVBVVhP3BdX0.jpg"
            )
        )
    }
}
