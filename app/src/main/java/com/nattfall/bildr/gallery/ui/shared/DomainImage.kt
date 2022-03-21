package com.nattfall.bildr.gallery.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.nattfall.bildr.R
import com.nattfall.bildr.data.requestRepsonse.flickr.PhotoDomainData

@Composable
fun DomainImage(
    modifier: Modifier = Modifier,
    data: PhotoDomainData,
    imageHeight: Dp = 280.dp,
) {
    val photoImageRequest = ImageRequest
        .Builder(LocalContext.current)
        .crossfade(true)
        .data(data.thumbnailUrl)
        .build()

    Image(
        modifier = modifier
            .height(imageHeight)
            .fillMaxWidth()
            .padding(8.dp),
        painter = rememberImagePainter(request = photoImageRequest),
        contentDescription = stringResource(
            id = R.string.gallery_success_image_description,
            data.photo.title
        )
    )
}