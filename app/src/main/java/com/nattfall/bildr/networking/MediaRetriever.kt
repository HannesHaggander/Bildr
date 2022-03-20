package com.nattfall.bildr.networking

import com.nattfall.bildr.data.requestRepsonse.flickr.PhotoDomainData

interface MediaRetriever {

    suspend fun queryPhoto(textSearch: String, page: Int): Result<List<PhotoDomainData>>

}
