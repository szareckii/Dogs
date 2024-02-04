package com.zareckii.dogs.ui.breed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zareckii.dogs.data.successOr
import com.zareckii.dogs.ui.breed.models.BreedViewState
import com.zareckii.dogs.domain.GetImageRandomUseCase
import com.zareckii.dogs.domain.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedViewModel @Inject constructor(
    private val getImageRandomUseCase: GetImageRandomUseCase,
    private val getImageUseCase: GetImagesUseCase,
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
                    val images = getImageUseCase(breed).successOr(null)
                    _viewState.update { it.copy(images = images ?: emptyList()) }
                }
            }
    }

    fun onClickLike() {

    }

    fun onClickNext() {
        val nextImage = _viewState.value.images.random()
        _viewState.update { it.copy(currentImage = nextImage) }
    }

}