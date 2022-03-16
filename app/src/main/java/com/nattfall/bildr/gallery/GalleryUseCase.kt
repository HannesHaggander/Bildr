package com.nattfall.bildr.gallery

import com.nattfall.bildr.gallery.data.ImageData

interface GalleryUseCase {

    suspend fun getImagesForCategories(categories: List<String>): List<ImageData> {
        return emptyList()
    }

}