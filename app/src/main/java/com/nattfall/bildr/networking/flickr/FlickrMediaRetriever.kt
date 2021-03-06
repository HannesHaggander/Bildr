package com.nattfall.bildr.networking.flickr

import com.nattfall.bildr.data.requestRepsonse.flickr.PhotoDomainData
import com.nattfall.bildr.data.requestRepsonse.flickr.toDomainModel
import com.nattfall.bildr.networking.MediaRetriever
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FlickrMediaRetriever @Inject constructor(
    private val flickrRequests: FlickrRequests,
) : MediaRetriever {

    override suspend fun queryPhoto(
        text: String,
        page: Int
    ): Result<List<PhotoDomainData>> {
        flickrRequests
            .search(text = text)
            .enqueueToResponse()
            .onSuccess { result ->
                return Result.success(result.photos.photo.map { it.toDomainModel() })
            }
            .onFailure { exception ->
                return Result.failure(exception = exception)
            }

        return Result.failure(exception = Throwable("Unknown failure"))
    }

}

// primarily simplified callback used to debug performed calls
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
