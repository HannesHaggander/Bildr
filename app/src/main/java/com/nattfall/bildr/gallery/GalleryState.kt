package com.nattfall.bildr.gallery

sealed class GalleryState {

    object Loading : GalleryState()

    class Failure(throwable: Throwable) : GalleryState()

    object Success : GalleryState()

}