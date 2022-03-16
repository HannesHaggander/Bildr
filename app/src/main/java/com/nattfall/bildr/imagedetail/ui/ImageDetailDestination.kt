package com.nattfall.bildr.imagedetail.ui

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
import androidx.navigation.NavController
import com.nattfall.bildr.imagedetail.ui.landscape.ImageDetailLandscape
import com.nattfall.bildr.imagedetail.ui.portrait.ImageDetailPortrait
import com.nattfall.bildr.navigation.NavRoute

@Composable
fun ImageDetailDestination(
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
                Configuration.ORIENTATION_LANDSCAPE -> ImageDetailLandscape(modifier)
                Configuration.ORIENTATION_PORTRAIT -> ImageDetailPortrait(modifier)
            }

            Button(onClick = { navController.navigate(NavRoute.Gallery.routeName) }) {
                Text(text = "To Gallery view")
            }
        }
    }
}
