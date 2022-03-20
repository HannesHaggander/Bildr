package com.nattfall.bildr.networking

import com.nattfall.bildr.data.requestRepsonse.flickr.SearchData
import retrofit2.Call

interface MediaRetriever {

    suspend fun queryPhoto(textSearch: String, page: Int): Result<SearchData>

}
