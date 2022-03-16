package com.nattfall.bildr.networking.flickr

import com.nattfall.bildr.data.requestRepsonse.flickr.SearchData
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchQuery {

    @GET("flickr.photos.search")
    fun search(@QueryMap parameters: Map<String, String>): SearchData

    companion object {
        const val PARAMETER_TEXT = "text"
        const val PARAMETER_SORT = "sort"
        const val PARAMETER_PAGE = "page"
        const val PARAMETER_PER_PAGE = "per_page"
    }

}