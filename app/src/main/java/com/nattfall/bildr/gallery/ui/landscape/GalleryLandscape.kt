package com.nattfall.bildr.gallery.ui.landscape

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.nattfall.bildr.gallery.ui.portrait.GalleryPortrait
import com.nattfall.bildr.ui.theme.AppTheme

@Composable
fun GalleryLandScape(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Temporary gallery landscape view",
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(name = "landscape gallery", widthDp = 1920, heightDp = 1080)
@Composable
fun GalleryPortrait_lightMode() {
    AppTheme(darkTheme = false) {
        GalleryPortrait()
    }
}