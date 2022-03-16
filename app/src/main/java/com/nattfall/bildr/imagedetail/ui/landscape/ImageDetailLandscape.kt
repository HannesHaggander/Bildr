package com.nattfall.bildr.imagedetail.ui.landscape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nattfall.bildr.ui.theme.AppTheme

@Composable
fun ImageDetailLandscape(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Image Detail landscape view",
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Preview(name = "landscape image detail", widthDp = 1920, heightDp = 1080)
@Composable
fun GalleryPortrait_lightMode() {
    AppTheme(darkTheme = false) {
        ImageDetailLandscape()
    }
}
