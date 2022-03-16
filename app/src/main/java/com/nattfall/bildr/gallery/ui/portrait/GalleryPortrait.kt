package com.nattfall.bildr.gallery.ui.portrait

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.nattfall.bildr.ui.theme.AppTheme

@Composable
fun GalleryPortrait(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Temporary gallery portrait view",
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(name = "portrait gallery", widthDp = 1080, heightDp = 1920)
@Composable
fun GalleryPortrait_lightMode() {
    AppTheme(darkTheme = false) {
        GalleryPortrait()
    }
}