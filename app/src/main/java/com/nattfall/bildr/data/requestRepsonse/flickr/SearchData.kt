package com.nattfall.bildr.data.requestRepsonse.flickr

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchData(
    val page: Int?,
    val pages: String?,
    val perPage: Int?,
    val total: String?,
    val photo: List<PhotoData>?,
    val stat: String?,
)
