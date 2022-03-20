package com.nattfall.bildr.gallery.data

import com.nattfall.bildr.data.requestRepsonse.flickr.SearchData

sealed class GalleryViewState {

    class Success(val data: SearchData) : GalleryViewState()

    class Error(val exception: Exception) : GalleryViewState()

    object Loading : GalleryViewState()

}
