package com.escalondev.movies_app_kmp.android.ui.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escalondev.domain.model.Video
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.ui.component.GenericWebView
import com.escalondev.movies_app_kmp.android.util.getVideoUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YouTubePlayerBottomSheet(
    modifier: Modifier = Modifier,
    video: Video,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    PlayerBottomSheetContent(
        modifier = modifier,
        video = video,
        sheetState = sheetState,
        onDismiss = onDismiss
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerBottomSheetContent(
    modifier: Modifier,
    video: Video,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = video.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                ),
            )
            GenericWebView(
                modifier = Modifier.height(300.dp),
                url = getVideoUrl(video.key),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun YouTubePlayerBottomSheetPreview() {
    MoviesAppTheme {
        PlayerBottomSheetContent(
            modifier = Modifier,
            video = Video(id = "", key = "key", name = "Sonic"),
            sheetState = rememberModalBottomSheetState(),
            onDismiss = {},
        )
    }
}
