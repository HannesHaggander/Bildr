package com.nattfall.bildr.networking.flickr

import com.nattfall.bildr.data.requestRepsonse.flickr.SearchData
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FlickrRequests {

    @GET("flickr.photos.search")
    fun search(@QueryMap parameters: Map<String, String>): SearchData

    companion object {
        const val PARAMETER_SEARCH_TEXT = "text"
        const val PARAMETER_SEARCH_SORT = "sort"
        const val PARAMETER_SEARCH_PAGE = "page"
        const val PARAMETER_SEARCH_PER_PAGE = "per_page"
    }

}