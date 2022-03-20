package com.nattfall.bildr.networking.flickr

import com.nattfall.bildr.data.requestRepsonse.flickr.SearchData
import com.nattfall.bildr.networking.MediaRetriever
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FlickrMediaRetriever @Inject constructor(
    private val flickrRequests: FlickrRequests
) : MediaRetriever {

    override suspend fun queryPhoto(
        text: String,
        page: Int
    ): Result<SearchData> {
        return flickrRequests
            .search(text = "cat")
            .enqueueToResponse()
//        return flickrRequests
//            .search(
//                text = text,
//                //page = page,
//            )
//            .enqueueToResponse()
    }
}

private suspend fun <T> Call<T>.enqueueToResponse(): Result<T> = suspendCoroutine { routine ->
    runCatching {
        enqueue((object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body()?.let { data ->
                    routine.resume(Result.success(data))
                    return
                } ?: run {
                    val error = Exception(response.errorBody()?.string().orEmpty())
                    routine.resume(Result.failure(error))
                    return
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                routine.resume(Result.failure(t))
                return
            }
        }))
    }
}