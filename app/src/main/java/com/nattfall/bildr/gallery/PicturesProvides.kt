package com.nattfall.bildr.gallery

import com.nattfall.bildr.data.repository.PhotosRepository
import com.nattfall.bildr.data.requestRepsonse.flickr.PhotoDomainData
import javax.inject.Inject

class PicturesProvides @Inject constructor(
    private val photosRepository: PhotosRepository
) : PicturesUseCase {

    override suspend fun searchPhotosForQuery(
        query: String,
        page: Int
    ): Result<List<PhotoDomainData>> {
        return photosRepository.queryPhotos(query = query, page = page)
    }

}
