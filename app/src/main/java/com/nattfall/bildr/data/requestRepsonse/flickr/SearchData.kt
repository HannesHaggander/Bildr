package com.nattfall.bildr.data.requestRepsonse.flickr

data class SearchData(
    val page: Int,
    val pages: String,
    val perPage: Int,
    val total: String,
    val photo: List<PhotoData>,
    val stat: String,
)
