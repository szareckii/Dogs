package com.zareckii.dogs.ui.breeddetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zareckii.dogs.data.successOr
import com.zareckii.dogs.domain.FetchImagesDbUseCase
import com.zareckii.dogs.ui.breeddetail.models.BreedDetailViewState
import com.zareckii.dogs.domain.GetImageRandomUseCase
import com.zareckii.dogs.domain.GetImageUseCase
import com.zareckii.dogs.domain.GetImagesUseCase
import com.zareckii.dogs.domain.ImageFavorite
import com.zareckii.dogs.domain.UpdateImageFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailViewModel @Inject constructor(
    private val getImageRandomUseCase: GetImageRandomUseCase,
    private val getImagesUseCase: GetImagesUseCase,
    private val fetchImagesDbUseCase: FetchImagesDbUseCase,
    private val updateImageFavoriteUseCase: UpdateImageFavoriteUseCase,
    private val getImageUseCase: GetImageUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(BreedDetailViewState())
    val viewState: StateFlow<BreedDetailViewState> = _viewState

    fun init(breed: String) {
        if (_viewState.value.currentImage == null)
            viewModelScope.launch {
                launch {
                    val imageUrl = getImageRandomUseCase(breed).successOr(null)
                    _viewState.update {
                        it.copy(
                            breedName = breed,
                            isLoading = false,
                            currentImage = imageUrl
                        )
                    }
                }

                launch {
                    val imagesFlow = getImagesUseCase(breed).successOr(emptyFlow())
                    imagesFlow.distinctUntilChanged().collect { images ->
                        _viewState.update { it.copy(images = images) }
                    }
                }

                launch {
                    fetchImagesDbUseCase(breed)
                }
            }
    }

    fun onClickLike() {
        viewModelScope.launch {
            val imageUrl = _viewState.value.currentImage?.imageUrl ?: ""
            val isFavorite = _viewState.value.currentImage?.isFavorite ?: false
            updateImageFavoriteUseCase(ImageFavorite(imageUrl, !isFavorite))
            val image = getImageUseCase(imageUrl).successOr(null)
            _viewState.update { it.copy(currentImage = image) }
        }
    }

    fun onClickNext() {
        val nextImage = _viewState.value.images.randomOrNull()
        _viewState.update { it.copy(currentImage = nextImage) }
    }

}
