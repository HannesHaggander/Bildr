package com.nattfall.bildr.gallery.ui.portrait

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.nattfall.bildr.R
import com.nattfall.bildr.data.requestRepsonse.flickr.PhotoDomainData
import com.nattfall.bildr.gallery.data.GalleryViewState
import com.nattfall.bildr.gallery.ui.SearchField
import com.nattfall.bildr.ui.theme.AppTheme

@Composable
fun GalleryPortrait(
    modifier: Modifier = Modifier,
    viewState: GalleryViewState,
    onSearchDone: (String) -> Unit = {},
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (search, results) = createRefs()

        SearchField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(search) {
                    bottom.linkTo(parent.bottom)
                },
            onSearchDone = onSearchDone
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(results) {
                    bottom.linkTo(search.top)
                    top.linkTo(parent.top)
                    height = Dimension.fillToConstraints
                },
            verticalArrangement = Arrangement.Top
        ) {
            when (val state = viewState) {
                is GalleryViewState.Success -> SuccessState(
                    modifier = modifier.fillMaxSize(),
                    photos = state.data
                )
                is GalleryViewState.Error -> ErrorState(
                    message = state.exception.message.orEmpty()
                )
                GalleryViewState.Loading -> LoadingState()
                GalleryViewState.Initial -> InitialState(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun InitialState(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(stringResource(R.string.initiate_search))
    }
}

@Composable
private fun SuccessState(
    modifier: Modifier = Modifier,
    photos: List<PhotoDomainData>
) {
    if (photos.isEmpty()) {
        FullScreenCard {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.found_no_photos)
            )
        }
        return
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 8.dp)
    ) {
        photos.forEach { data ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(4.dp),
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Column {
                    DomainImage(data = data)

                    CenteredText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 8.dp),
                        text = data.photo.title
                    )
                }
            }
        }
    }

    scrollState.value
}


@Composable
fun DomainImage(
    modifier: Modifier = Modifier,
    data: PhotoDomainData
) {
    val photoImageRequest = ImageRequest
        .Builder(LocalContext.current)
        .crossfade(true)
        .data(data.thumbnailUrl)
        .build()

    Image(
        modifier = modifier
            .height(280.dp)
            .fillMaxWidth()
            .padding(8.dp),
        painter = rememberImagePainter(request = photoImageRequest),
        contentDescription = stringResource(
            id = R.string.gallery_success_image_description,
            data.photo.title
        )
    )
}

@Composable
private fun ErrorState(message: String) {
    FullScreenCard {
        Column {
            CenteredText(
                modifier = Modifier.padding(8.dp),
                text = stringResource(id = R.string.gallery_failed_state_message)
            )
            CenteredText(
                modifier = Modifier.padding(8.dp),
                text = message
            )
        }
    }
}

@Composable
private fun LoadingState() {
    FullScreenCard {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(8.dp))
            CenteredText(text = stringResource(id = R.string.gallery_loading_state_message))
        }
    }
}

@Composable
fun FullScreenCard(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.padding(8.dp),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            content.invoke()
        }
    }
}

@Composable
fun CenteredText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center,
    )
}


@Preview("Success state preview")
@Composable
fun PreviewSuccessState() {
    AppTheme {
        SuccessState(
            modifier = Modifier.fillMaxSize(),
            photos = emptyList()
        )
    }
}

@Preview("error state with message")
@Composable()
fun PreviewErrorState() {
    AppTheme {
        ErrorState(message = "Preview error")
    }
}

@Preview("loading state")
@Composable
fun PreviewLoadingState() {
    AppTheme {
        LoadingState()
    }
}

@Preview("Initial state")
@Composable
fun PreviewInitialState() {
    AppTheme {
        InitialState()
    }
}