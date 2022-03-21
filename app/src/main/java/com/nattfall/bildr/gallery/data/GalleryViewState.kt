package com.nattfall.bildr.gallery.data

import com.nattfall.bildr.data.requestRepsonse.flickr.PhotoDomainData

sealed class GalleryViewState {

    object Initial : GalleryViewState()

    class Success(val data: List<PhotoDomainData>) : GalleryViewState()

    class Error(val exception: Exception) : GalleryViewState()

    object Loading : GalleryViewState()

}
