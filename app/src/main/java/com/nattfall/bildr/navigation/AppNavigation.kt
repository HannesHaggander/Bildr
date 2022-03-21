package com.nattfall.bildr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nattfall.bildr.data.requestRepsonse.flickr.joinUrlFromDeepLink
import com.nattfall.bildr.gallery.ui.GalleryDestination
import com.nattfall.bildr.imagedetail.ui.ImageDetailDestination

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Gallery.routeName
    ) {
        composable(NavRoute.Gallery.routeName) {
            GalleryDestination(navController = navController)
        }

        composable(
            NavRoute
                .ImageDetail
                .routeName
                .plusArg(ARG_IMAGE_DETAIL_TITLE)
                .plusArg(ARG_IMAGE_DETAIL_SAFE_URL)
        ) { backstackEntry ->
            val imageTitle = backstackEntry.arguments?.getString(ARG_IMAGE_DETAIL_TITLE)
            val safeUrl = backstackEntry.arguments?.getString(ARG_IMAGE_DETAIL_SAFE_URL)

            ImageDetailDestination(
                navController = navController,
                imageUrl = safeUrl?.joinUrlFromDeepLink().orEmpty(),
                title = imageTitle.orEmpty()
            )
        }
    }
}

private fun String.plusArg(argument: String) = plus("/{$argument}")

private fun String.plusNavTarget(argument: String) = plus("/$argument")

fun NavController.navToImageDetail(title: String, url: String) {
    NavRoute
        .ImageDetail
        .routeName
        .plusNavTarget(url)
        .plusNavTarget(title)
        .let { target -> navigate(target) }
}

private const val ARG_IMAGE_DETAIL_TITLE = "title"
private const val ARG_IMAGE_DETAIL_SAFE_URL = "safeurl"

