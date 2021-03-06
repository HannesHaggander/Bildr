package com.nattfall.bildr.gallery.ui.landscape

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun GalleryLandScape(
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
                is GalleryViewState.Success -> LandscapeSuccessState(
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
private fun LandscapeSuccessState(
    modifier: Modifier = Modifier,
    photos: List<PhotoDomainData>,
    onItemClick: (PhotoDomainData) -> Unit = {}
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

    Row(
        modifier = modifier
            .horizontalScroll(scrollState)
            .padding(horizontal = 8.dp)
    ) {
        photos.forEach { data ->
            Card(
                modifier = Modifier
                    .width(180.dp)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .clickable {
                        onItemClick.invoke(data)
                    },
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Column {
                    DomainImage(
                        modifier = Modifier.clickable { onItemClick(data) },
                        data = data,
                        imageHeight = 180.dp
                    )

                    Text(
                        modifier = Modifier
                            .width(180.dp)
                            .fillMaxHeight()
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
