package com.escalondev.movies_app_kmp.android.ui.profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.escalondev.movies_app_kmp.android.R
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.ui.base.BaseScreen
import com.escalondev.movies_app_kmp.android.ui.component.BaseAppBar
import com.escalondev.movies_app_kmp.android.ui.component.CircleAvatar
import com.escalondev.movies_app_kmp.android.ui.component.SquaredPrimaryButton
import com.escalondev.movies_app_kmp.domain.util.getSizedImage

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.onUiEvent(ProfileUiEvent.OnStart)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProfileContent(uiState, viewModel::onUiEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    uiState: ProfileUiState = ProfileUiState(),
    onUiEvent: (ProfileUiEvent) -> Unit = {}
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
                uiState = uiState,
                onEvent = onUiEvent
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileOptions { title ->
                onUiEvent(ProfileUiEvent.OnProfileOptionClick(title))
            }
        }
    }
}

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    onEvent: (ProfileUiEvent) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val editProfileLabel = stringResource(id = R.string.profile_edit_profile)
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
        Spacer(modifier = Modifier.height(8.dp))
        SquaredPrimaryButton(
            modifier = Modifier.fillMaxWidth(fraction = 0.6f),
            text = editProfileLabel,
            onClick = {
                onEvent(ProfileUiEvent.OnEditProfileClick(editProfileLabel))
            }
        )
    }
}

@Composable
fun ProfileOptions(
    modifier: Modifier = Modifier,
    onProfileItemClick: (String) -> Unit
) {
    val context = LocalContext.current

    Column(modifier = modifier) {
        ProfileOptionItem(
            text = R.string.profile_app_settings,
            onItemClick = { onProfileItemClick(getString(context, R.string.profile_app_settings)) }
        )
        ProfileOptionItem(
            text = R.string.profile_account_policy,
            onItemClick = { onProfileItemClick(context.getString(R.string.profile_account_policy)) }
        )
        ProfileOptionItem(
            text = R.string.profile_privacy_policy,
            onItemClick = { onProfileItemClick(context.getString(R.string.profile_privacy_policy)) }
        )
        ProfileOptionItem(
            text = R.string.profile_credits,
            onItemClick = { onProfileItemClick(context.getString(R.string.profile_credits)) }
        )
        ProfileOptionItem(
            text = R.string.profile_log_out,
            onItemClick = { onProfileItemClick(context.getString(R.string.profile_log_out)) }
        )
        Text(
            modifier = Modifier.padding(all = 16.dp),
            text = stringResource(id = R.string.general_app_version),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ProfileOptionItem(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    onItemClick: () -> Unit = {}
) {
    Column(modifier = modifier.clickable { onItemClick() }) {
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            headlineContent = { Text(text = stringResource(id = text)) },
            trailingContent = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = ""
                )
            }
        )
        HorizontalDivider(Modifier.padding(start = 16.dp))
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
