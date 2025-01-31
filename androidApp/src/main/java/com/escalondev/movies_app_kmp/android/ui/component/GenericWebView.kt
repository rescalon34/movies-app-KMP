package com.escalondev.movies_app_kmp.android.ui.component

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme

/**
 * Generic web view to load any url
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GenericWebView(
    modifier: Modifier = Modifier,
    url: String
) {

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        }, update = { it.loadUrl(url) }
    )
}

@Preview
@Composable
private fun GenericWebViewPreview() {
    MoviesAppTheme {
        GenericWebView(url = "https://www.google.com")
    }
}
