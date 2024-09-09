package com.escalondev.movies_app_kmp.android.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme

@Composable
fun SettingsScreen() {
    SettingsContent()
}

@Composable
fun SettingsContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Settings Screen", style = MaterialTheme.typography.titleLarge)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SettingsContentPreview() {
    MoviesAppTheme {
        SettingsContent()
    }
}
