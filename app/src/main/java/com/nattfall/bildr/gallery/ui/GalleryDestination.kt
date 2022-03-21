package com.nattfall.bildr.gallery.ui

import android.content.res.Configuration
import android.text.TextUtils
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nattfall.bildr.data.requestRepsonse.flickr.PhotoDomainData
import com.nattfall.bildr.data.requestRepsonse.flickr.splitUrlToDeepLink
import com.nattfall.bildr.gallery.GalleryViewModel
import com.nattfall.bildr.gallery.data.GalleryViewState
import com.nattfall.bildr.gallery.ui.landscape.GalleryLandScape
import com.nattfall.bildr.gallery.ui.portrait.GalleryPortrait
import com.nattfall.bildr.navigation.NavRoute
import com.nattfall.bildr.navigation.navToImageDetail

@Composable
fun GalleryDestination(
    modifier: Modifier = Modifier,
    orientation: Int = LocalConfiguration.current.orientation,
    navController: NavController,
    galleryViewModel: GalleryViewModel = hiltViewModel(),
) {
    val onSearchDone: (String) -> Unit = { query ->
        galleryViewModel.queryPhotos(query)
    }

    val onPictureClicked: (PhotoDomainData) -> Unit = { data ->
        navController.navToImageDetail(
            data.fullImageUrl.splitUrlToDeepLink(),
            data.photo.title
        )
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val state = galleryViewModel.viewState.collectAsState()

        when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> GalleryLandScape(
                viewState = state.value,
                onSearchDone = onSearchDone,
                onItemClick = onPictureClicked,
            )
            Configuration.ORIENTATION_PORTRAIT -> GalleryPortrait(
                viewState = state.value,
                onSearchDone = onSearchDone,
                onItemClick = onPictureClicked,
            )
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
            GalleryPortrait(modifier, GalleryViewState.Loading) {}
            Button(onClick = { navController.navigate(NavRoute.ImageDetail.routeName) }) {
                Text(text = "To image detail view")
            }
        }
    }
}
