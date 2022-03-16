package com.nattfall.bildr.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nattfall.bildr.data.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
) : ViewModel() {

    fun queryPhotos(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                photosRepository.queryPhotos(query = query, page = 1)
            }.onSuccess {
                val d = it
                1
            }.onFailure { error ->
                val e = error
                1
            }
        }
    }
}