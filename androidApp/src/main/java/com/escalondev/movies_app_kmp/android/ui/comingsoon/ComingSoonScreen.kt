package com.escalondev.movies_app_kmp.android.ui.comingsoon

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.ui.base.BaseScreen
import com.escalondev.movies_app_kmp.android.ui.component.BaseAppBar
import com.escalondev.movies_app_kmp.android.ui.component.InfoMessageCard

@Composable
fun ComingSoonScreen(viewModel: ComingSoonViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ComingSoonContent(uiState, viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComingSoonContent(
    uiState: ComingSoonUiState = ComingSoonUiState(),
    onEvent: (ComingSoonUiEvent) -> Unit = {}
) {
    BaseScreen(
        topBar = {
            BaseAppBar(
                title = uiState.title,
                showNavigationIcon = true,
                onBackButtonClick = { onEvent(ComingSoonUiEvent.OnNavigateBack) }
            )
        }
    ) {
        InfoMessageCard()
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun ComingSoonScreenPreview() {
    MoviesAppTheme {
        ComingSoonScreen()
    }
}
