package com.escalondev.movies_app_kmp.android.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.util.DEFAULT_POSTER_SIZE
import com.escalondev.movies_app_kmp.domain.util.getSizedImage

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    cardShape: Shape = CardDefaults.shape,
    movie: Movie,
    imageSize: String = DEFAULT_POSTER_SIZE
) {
    Card(
        modifier = modifier,
        shape = cardShape
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = movie.imageUrl?.getSizedImage(imageSize),
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
                id = 0,
                title = "",
                imageUrl = "https://media.themoviedb.org/t/p/original/t7bhjraXuN4hd3yZVBVVhP3BdX0.jpg",
                releaseDate = "2024-10-09",
            )
        )
    }
}
