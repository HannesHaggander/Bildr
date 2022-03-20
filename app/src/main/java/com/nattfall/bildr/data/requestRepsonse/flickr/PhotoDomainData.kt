package com.nattfall.bildr.data.requestRepsonse.flickr

data class PhotoDomainData(
    val photo: PhotoData,
    val thumbnailUrl: String,
    val fullImageUrl: String,
)
