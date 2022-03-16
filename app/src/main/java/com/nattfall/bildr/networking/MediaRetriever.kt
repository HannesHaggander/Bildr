package com.nattfall.bildr.networking

import com.nattfall.bildr.data.requestRepsonse.flickr.SearchData

interface MediaRetriever {

    suspend fun queryPhoto(textSearch: String, page: Int): SearchData

}
