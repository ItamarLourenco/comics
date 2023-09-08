package com.example.comics.application.feature.widget

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

@Composable
fun HtmlTextView(
    htmlText: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context).apply {
                text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT)
            }
        }
    )
}