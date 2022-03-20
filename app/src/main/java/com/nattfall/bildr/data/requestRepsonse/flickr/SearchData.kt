package com.nattfall.bildr.data.requestRepsonse.flickr

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchData(
    val photos: PhotosData,
    val stat: String?,
)
