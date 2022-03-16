package com.nattfall.bildr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nattfall.bildr.gallery.ui.GalleryDestination
import com.nattfall.bildr.imagedetail.ui.ImageDetailDestination

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Gallery.routeName
    ) {
        composable(NavRoute.Gallery.routeName) { GalleryDestination(navController = navController) }
        composable(NavRoute.ImageDetail.routeName) { ImageDetailDestination(navController = navController) }
    }
}