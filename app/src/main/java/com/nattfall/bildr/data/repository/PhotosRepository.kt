package com.nattfall.bildr.data.repository

import com.nattfall.bildr.data.requestRepsonse.flickr.PhotoDomainData
import com.nattfall.bildr.networking.MediaRetriever
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val mediaRetriever: MediaRetriever
) {

    suspend fun queryPhotos(query: String, page: Int = 1): Result<List<PhotoDomainData>> {
        return mediaRetriever.queryPhoto(query, page)
    }

}