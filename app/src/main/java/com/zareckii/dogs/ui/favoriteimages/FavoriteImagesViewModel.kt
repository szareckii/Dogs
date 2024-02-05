package com.zareckii.dogs.ui.favoriteimages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zareckii.dogs.data.successOr
import com.zareckii.dogs.domain.GetFavoriteImagesUseCase
import com.zareckii.dogs.domain.ImageFavorite
import com.zareckii.dogs.domain.UpdateImageFavoriteUseCase
import com.zareckii.dogs.ui.favoriteimages.models.FavoriteImagesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteImagesViewModel @Inject constructor(
    private val getFavoriteImagesUseCase: GetFavoriteImagesUseCase,
    private val updateImageFavoriteUseCase: UpdateImageFavoriteUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(FavoriteImagesViewState())
    val viewState: StateFlow<FavoriteImagesViewState> = _viewState

    fun init(breed: String) {
        viewModelScope.launch {
            launch {
                val imagesFlow = getFavoriteImagesUseCase(breed).successOr(emptyFlow())
                imagesFlow
                    .collect { images ->
                        _viewState.update {
                            it.copy(
                                images = images,
                                isLoading = false
                            )
                        }
                    }
            }
        }
    }

    fun removeFavoriteImage(imageUrl: String) {
        viewModelScope.launch {
            updateImageFavoriteUseCase(ImageFavorite(imageUrl, isFavorite = false))
        }
    }
}
