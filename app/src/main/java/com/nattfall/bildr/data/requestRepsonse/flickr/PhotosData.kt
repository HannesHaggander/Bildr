package com.nattfall.bildr.data.requestRepsonse.flickr

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosData(
    val page: Int,
    val pages: String,
    val perPage: Int?,
    val total: String,
    val photo: List<PhotoData>,
)

