package com.escalondev.movies_app_kmp.android.ui.profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.escalondev.movies_app_kmp.android.R
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.ui.base.BaseScreen
import com.escalondev.movies_app_kmp.android.ui.component.BaseAppBar
import com.escalondev.movies_app_kmp.android.ui.component.CircleAvatar
import com.escalondev.movies_app_kmp.domain.util.getSizedImage

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProfileContent(uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    uiState: ProfileUiState = ProfileUiState()
) {
    BaseScreen(
        topBar = { BaseAppBar(title = stringResource(R.string.profile_title)) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProfileHeader(
                modifier = Modifier.padding(16.dp),
                uiState = uiState
            )
        }
    }
}

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircleAvatar(
            modifier = Modifier.size(140.dp),
            imageUrl = uiState.imageUrl.getSizedImage()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = uiState.userName,
            style = MaterialTheme.typography.titleMedium
                .copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun ProfileContentPreview() {
    MoviesAppTheme {
        ProfileContent(
            uiState = ProfileUiState(
                userName = "rescalon34",
                name = "Roberto",
            )
        )
    }
}
