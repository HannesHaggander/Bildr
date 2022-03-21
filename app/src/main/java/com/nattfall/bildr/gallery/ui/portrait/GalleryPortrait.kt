package com.nattfall.bildr.gallery.ui.portrait

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.nattfall.bildr.R
import com.nattfall.bildr.data.requestRepsonse.flickr.PhotoDomainData
import com.nattfall.bildr.gallery.data.GalleryViewState
import com.nattfall.bildr.gallery.ui.ErrorState
import com.nattfall.bildr.gallery.ui.FullScreenCard
import com.nattfall.bildr.gallery.ui.SearchField
import com.nattfall.bildr.gallery.ui.shared.DomainImage
import com.nattfall.bildr.gallery.ui.shared.InitialState
import com.nattfall.bildr.gallery.ui.shared.LoadingState
import com.nattfall.bildr.ui.theme.AppTheme

@Composable
fun GalleryPortrait(
    modifier: Modifier = Modifier,
    viewState: GalleryViewState,
    onItemClick: (PhotoDomainData) -> Unit = {},
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(results) {
                    bottom.linkTo(search.top)
                    top.linkTo(parent.top)
                    height = Dimension.fillToConstraints
                }
        ) {
            when (val state = viewState) {
                is GalleryViewState.Success -> PortraitSuccessState(
                    modifier = modifier.fillMaxSize(),
                    photos = state.data,
                    onItemClick = onItemClick
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
private fun PortraitSuccessState(
    modifier: Modifier = Modifier,
    photos: List<PhotoDomainData>,
    onItemClick: (PhotoDomainData) -> Unit = {},
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
                    .padding(4.dp)
                    .clickable { onItemClick.invoke(data) },
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Column {
                    DomainImage(
                        modifier = Modifier.clickable { onItemClick(data) },
                        data = data
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 8.dp),
                        text = data.photo.title,
                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview("Success state preview")
@Composable
fun PreviewSuccessState() {
    AppTheme {
        PortraitSuccessState(
            modifier = Modifier.fillMaxSize(),
            photos = emptyList(),
            onItemClick = {}
        )
    }
}

@Preview("error state with message")
@Composable
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