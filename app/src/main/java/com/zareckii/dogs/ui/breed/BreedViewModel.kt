package com.zareckii.dogs.ui.breed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zareckii.dogs.data.successOr
import com.zareckii.dogs.domain.FetchImagesDbUseCase
import com.zareckii.dogs.ui.breed.models.BreedViewState
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
class BreedViewModel @Inject constructor(
    private val getImageRandomUseCase: GetImageRandomUseCase,
    private val getImagesUseCase: GetImagesUseCase,
    private val fetchImagesDbUseCase: FetchImagesDbUseCase,
    private val updateImageFavoriteUseCase: UpdateImageFavoriteUseCase,
    private val getImageUseCase: GetImageUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(BreedViewState())
    val viewState: StateFlow<BreedViewState> = _viewState

    fun init(breed: String) {
        if (_viewState.value.currentImage == null)
            viewModelScope.launch {

                launch {
                    val imageUrl = getImageRandomUseCase(breed).successOr(null)
                    _viewState.update {
                        it.copy(
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
                    Log.e("stas", "fetchImagesDbUseCase!!!!!!!!!!")

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

        Log.e("stas", "nextImage -> $nextImage")

        _viewState.update { it.copy(currentImage = nextImage) }
    }

}