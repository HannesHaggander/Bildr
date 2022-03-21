package com.nattfall.bildr.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nattfall.bildr.data.requestRepsonse.flickr.PhotoDomainData
import com.nattfall.bildr.gallery.data.GalleryViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val galleryUseCase: PicturesUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow<GalleryViewState>(value = GalleryViewState.Initial)
    val viewState: StateFlow<GalleryViewState> get() = _viewState

    private var lastSearchQuery: String = ""
    private val photoQueryResult: MutableList<PhotoDomainData> = mutableListOf()

    fun queryPhotos(query: String, page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.emit(GalleryViewState.Loading)
            galleryUseCase
                .searchPhotosForQuery(query = query, page = page)
                .onSuccess { searchData ->
                    photoQueryResult.apply {
                        if (lastSearchQuery != query) {
                            clear()
                        }
                        addAll(searchData)
                    }

                    lastSearchQuery = query
                    _viewState.emit(GalleryViewState.Success(photoQueryResult))
                }
                .onFailure { error ->
                    _viewState.emit(GalleryViewState.Error(exception = Exception(error)))
                }
        }
    }
}
