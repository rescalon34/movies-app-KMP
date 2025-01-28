package com.escalondev.movies_app_kmp.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.escalondev.movies_app_kmp.android.R
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.theme.customColors
import com.escalondev.movies_app_kmp.domain.util.getSizedImage

@Composable
fun CircleAvatar(
    modifier: Modifier = Modifier,
    borderColor: List<Color> = MaterialTheme.customColors.primaryGradient,
    imageUrl: String? = ""
) {
    val isPreview = LocalInspectionMode.current

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Brush.linearGradient(colors = borderColor))
            .padding(3.dp)
            .clip(CircleShape)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl?.getSizedImage(),
            placeholder = if (isPreview) painterResource(R.drawable.ic_placeholder) else null,
            contentScale = ContentScale.Crop, // Ensure image fills the circle
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun CircleAvatarPreview() {
    MoviesAppTheme {
        CircleAvatar(
            modifier = Modifier.size(160.dp),
            borderColor = MaterialTheme.customColors.primaryGradient,
            imageUrl = "https://media.themoviedb.org/t/p/original/t7bhjraXuN4hd3yZVBVVhP3BdX0.jpg"
        )
    }
}