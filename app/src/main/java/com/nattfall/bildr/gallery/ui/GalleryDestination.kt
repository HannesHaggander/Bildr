package com.nattfall.bildr.gallery.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nattfall.bildr.gallery.ui.landscape.GalleryLandScape
import com.nattfall.bildr.gallery.ui.portrait.GalleryPortrait
import com.nattfall.bildr.navigation.NavRoute

@Composable
fun GalleryDestination(
    modifier: Modifier = Modifier,
    navController: NavController,
    orientation: Int = LocalConfiguration.current.orientation
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            when (orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> GalleryLandScape()
                Configuration.ORIENTATION_PORTRAIT -> GalleryPortrait()
            }
            Button(onClick = { navController.navigate(NavRoute.ImageDetail.routeName) }) {
                Text(text = "To image detail view")
            }
        }
    }
}

@Preview
@Composable
fun GalleryPreview() {
    val modifier = Modifier
    val navController = rememberNavController()
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            GalleryPortrait(modifier)
            Button(onClick = { navController.navigate(NavRoute.ImageDetail.routeName) }) {
                Text(text = "To image detail view")
            }
        }
    }
}
