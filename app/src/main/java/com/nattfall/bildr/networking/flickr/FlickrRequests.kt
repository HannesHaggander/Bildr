package com.nattfall.bildr.networking.flickr

import com.nattfall.bildr.data.requestRepsonse.flickr.SearchData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface FlickrRequests {

    @GET
    fun search(
        @Url url: String = "",
        @Query("method") method: String = "flickr.photos.search",
        @Query(PARAMETER_SEARCH_TEXT) text: String,
        //@Query(PARAMETER_SEARCH_PAGE) page: Int = 1,
    ): Call<SearchData>

    @GET("?method=flickr.photos.search&text=cats")
    fun preparedSearch(): Call<SearchData>

    companion object {
        const val PARAMETER_SEARCH_TEXT = "text"
        const val PARAMETER_SEARCH_PAGE = "page"
    }
}
