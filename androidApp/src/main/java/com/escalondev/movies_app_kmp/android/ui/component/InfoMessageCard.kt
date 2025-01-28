package com.escalondev.movies_app_kmp.android.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escalondev.movies_app_kmp.android.R
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme

/**
 * Composable to show a tittle and description message in the center of the screen.
 */
@Composable
fun InfoMessageCard(
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.general_coming_soon_title),
    description: String? = stringResource(R.string.general_coming_soon_description)
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.titleMedium
                .copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                ),
        )
        description?.let {
            Text(
                text = it,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 4.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun InfoMessageCardPreview() {
    MoviesAppTheme {
        InfoMessageCard()
    }
}
