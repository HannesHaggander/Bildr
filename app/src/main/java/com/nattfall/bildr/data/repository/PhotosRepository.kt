package com.nattfall.bildr.data.repository

import com.nattfall.bildr.data.requestRepsonse.flickr.SearchData
import com.nattfall.bildr.networking.MediaRetriever
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val mediaRetriever: MediaRetriever
) {

    suspend fun queryPhotos(query: String, page: Int = 1): Result<SearchData> {
        return mediaRetriever.queryPhoto(query, page)
    }

}