package com.nattfall.bildr.networking.flickr

import com.nattfall.bildr.data.requestRepsonse.flickr.SearchData
import com.nattfall.bildr.networking.MediaRetriever
import retrofit2.Retrofit
import javax.inject.Inject

class FlickrMediaRetriever @Inject constructor(
    private val retrofit: Retrofit
) : MediaRetriever {

    private fun searchQuery(): SearchQuery = retrofit.create(SearchQuery::class.java)

    override suspend fun queryPhoto(
        text: String,
        page: Int
    ): SearchData {
        val queryMap = mapOf(
            SearchQuery.PARAMETER_TEXT to text,
            SearchQuery.PARAMETER_PAGE to page.toString(),
        )

        return searchQuery().search(queryMap)
    }
}