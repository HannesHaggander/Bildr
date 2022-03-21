package com.nattfall.bildr.data.requestRepsonse.flickr

data class PhotoDomainData(
    val photo: PhotoData,
    val previewImage: String,
    val fullImageUrl: String,
)
