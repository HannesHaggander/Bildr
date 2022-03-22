package com.nattfall.bildr.imagedetail.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.nattfall.bildr.R
import com.nattfall.bildr.ui.theme.AppTheme
import kotlin.math.absoluteValue


@Composable
fun ImageDetailDestination(
    modifier: Modifier = Modifier,
    navController: NavController,
    imageUrl: String,
    title: String,
) {
    if (imageUrl.isBlank()) {
        ErrorEmptyUrl()
        return
    }

    PictureScaffold(
        imageTitle = title,
        imageUrl = imageUrl,
        onBackPress = { navController.popBackStack() }
    )
}

@Composable
fun PictureScaffold(
    modifier: Modifier = Modifier,
    imageTitle: String,
    imageUrl: String,
    onBackPress: () -> Unit = { }
) {
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                onBackPress = { onBackPress.invoke() },
                imageTitle = imageTitle,
                onShareLinkPress = { shareImageLink(context = context, link = imageUrl) }
            )
        },
        content = {
            PictureView(
                imageUrl = imageUrl,
                title = imageTitle,
                onFlingToClose = { onBackPress.invoke() }
            )
        }
    )
}

@Composable
fun PictureView(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    onFlingToClose: () -> Unit = { },
    flingToCloseThreshold: Int = 50,
) {
    val photoImageRequest = ImageRequest
        .Builder(LocalContext.current)
        .crossfade(true)
        .data(imageUrl)
        .build()

    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        scale *= zoomChange
        offset += offsetChange
    }

    // sentinel value to block multiple calls to close when swiping
    var flingToCloseTriggered = false

    val draggableState = rememberDraggableState { delta ->
        if (!flingToCloseTriggered && delta.absoluteValue > flingToCloseThreshold) {
            flingToCloseTriggered = true
            onFlingToClose.invoke()
        }
    }

    Image(
        modifier = modifier
            .fillMaxSize()
            .draggable(
                state = draggableState,
                orientation = Orientation.Vertical
            )
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                translationX = offset.x
                translationY = offset.y
            }
            .transformable(state = state),
        painter = rememberImagePainter(request = photoImageRequest),
        contentDescription = stringResource(
            id = R.string.gallery_success_image_description,
            title
        )
    )
}

fun shareImageLink(context: Context, link: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, link)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(context, shareIntent, null)
}


@Composable
private fun ErrorEmptyUrl() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.error_malformed_image_url),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    imageTitle: String,
    onBackPress: () -> Unit,
    onShareLinkPress: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.primary)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = stringResource(R.string.navigate_back),
            modifier = Modifier
                .padding(8.dp)
                .size(32.dp)
                .clickable { onBackPress.invoke() }
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = imageTitle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Image(
            painter = painterResource(id = R.drawable.share),
            contentDescription = stringResource(R.string.share_link),
            modifier = Modifier
                .padding(8.dp)
                .size(32.dp)
                .clickable { onShareLinkPress.invoke() }
        )
    }
}

@Preview("Scaffold view")
@Composable
fun PreviewPictureScaffold() {
    AppTheme {
        PictureScaffold(
            imageTitle = "Preview title",
            imageUrl = "https://live.staticflickr.com/7372/12502775644_acfd415fa7_w.jpg"
        )
    }
}