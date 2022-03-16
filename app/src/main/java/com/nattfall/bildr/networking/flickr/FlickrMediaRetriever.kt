package com.nattfall.bildr.networking.flickr

import com.nattfall.bildr.data.requestRepsonse.flickr.SearchData
import com.nattfall.bildr.networking.MediaRetriever
import javax.inject.Inject

class FlickrMediaRetriever @Inject constructor(
    private val flickrRequests: FlickrRequests
) : MediaRetriever {

    override suspend fun queryPhoto(
        text: String,
        page: Int
    ): SearchData {
        val queryMap = mapOf(
            FlickrRequests.PARAMETER_SEARCH_TEXT to text,
            FlickrRequests.PARAMETER_SEARCH_PAGE to page.toString(),
        )

        return flickrRequests.search(queryMap)
    }
}