package com.escalondev.movies_app_kmp.android.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.ui.component.InfoMessageCard

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProfileContent(uiState)
}

@Composable
fun ProfileContent(
    uiState: ProfileUiState = ProfileUiState()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InfoMessageCard(title = uiState.userName, description = null)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProfileContentPreview() {
    MoviesAppTheme {
        ProfileContent()
    }
}
