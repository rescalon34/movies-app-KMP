package com.escalondev.movies_app_kmp.android.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme

@Composable
fun PlayButtonOverlay(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f))
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .clip(CircleShape)
                .align(Alignment.Center),
        ) {
            Icon(
                modifier = Modifier
                    .padding(8.dp),
                imageVector = Icons.Rounded.PlayArrow,
                contentDescription = "Play",
                tint = Color.Black
            )
        }
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PlayButtonOverlayPreview() {
    MoviesAppTheme {
        PlayButtonOverlay()
    }
}