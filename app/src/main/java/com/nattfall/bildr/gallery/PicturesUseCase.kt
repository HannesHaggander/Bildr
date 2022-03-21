package com.nattfall.bildr.gallery

import com.nattfall.bildr.data.requestRepsonse.flickr.PhotoDomainData

interface PicturesUseCase {

    suspend fun searchPhotosForQuery(query: String, page: Int): Result<List<PhotoDomainData>>

}
