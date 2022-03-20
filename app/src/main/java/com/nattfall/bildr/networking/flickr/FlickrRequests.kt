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
        @Query(PARAMETER_SEARCH_TEXT) text: String,
        //@Query(PARAMETER_SEARCH_PAGE) page: Int = 1,
        @Query("api_key") apiKey: String = "5dc21f3c1e455d9bbe056081eaa49be5",
        @Query("format") format: String = "json",
        @Query("method") method: String = "flickr.photos.search",
    ): Call<SearchData>

    @GET("?method=flickr.photos.search&text=cats")
    fun preparedSearch(): Call<SearchData>

    //@GET("?method=flickr.photos.search&api_key=80a9256d7649419ce654f3fe9205ef28&text=cats&format=json&nojsoncallback=1")

    companion object {
        const val PARAMETER_SEARCH_TEXT = "text"
        const val PARAMETER_SEARCH_PAGE = "page"
    }
}
