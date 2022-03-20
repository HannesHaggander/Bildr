package com.nattfall.bildr.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nattfall.bildr.data.repository.PhotosRepository
import com.nattfall.bildr.gallery.data.GalleryViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
) : ViewModel() {

    private val _viewState = MutableStateFlow<GalleryViewState>(value = GalleryViewState.Loading)
    val viewState: StateFlow<GalleryViewState> get() = _viewState

    fun queryPhotos(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.emit(GalleryViewState.Loading)
            photosRepository
                .queryPhotos(query = query, page = 1)
                .onSuccess { searchData ->
                    _viewState.emit(GalleryViewState.Success(searchData))
                }
                .onFailure { error ->
                    _viewState.emit(GalleryViewState.Error(exception = Exception(error)))
                }
        }
    }
}