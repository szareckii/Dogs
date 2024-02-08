package com.zareckii.dogs.ui.breeddetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zareckii.dogs.R
import com.zareckii.dogs.data.network.Result
import com.zareckii.dogs.data.network.successOr
import com.zareckii.dogs.domain.usecase.imageusecase.FetchImagesDbUseCase
import com.zareckii.dogs.ui.breeddetail.models.BreedDetailViewState
import com.zareckii.dogs.domain.usecase.imageusecase.GetImageRandomUseCase
import com.zareckii.dogs.domain.usecase.imageusecase.GetImageUseCase
import com.zareckii.dogs.domain.usecase.imageusecase.GetImagesUseCase
import com.zareckii.dogs.domain.model.ImageFavorite
import com.zareckii.dogs.domain.usecase.imageusecase.UpdateImageFavoriteUseCase
import com.zareckii.dogs.ui.breeddetail.models.BreedDetailAction
import com.zareckii.dogs.ui.breeds.models.BreedsAction
import com.zareckii.dogs.utils.ManageResources
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
    private val getImageUseCase: GetImageUseCase,
    private val resources: ManageResources
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
                    val result = fetchImagesDbUseCase(breed)

                    Log.e("stas", "result -> $result")

                    if (result is Result.Success)
                        _viewState.update { it.copy(breedDetailAction = BreedDetailAction.None) }
                    else {
                        _viewState.update {
                            it.copy(
                                breedDetailAction = BreedDetailAction.SnackbarShow,
                                snackbarMessage = resources.string(R.string.connection_error)
                            )
                        }
                    }
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

    fun actionInvoked() = _viewState.update { it.copy(breedDetailAction = BreedDetailAction.None) }

}
