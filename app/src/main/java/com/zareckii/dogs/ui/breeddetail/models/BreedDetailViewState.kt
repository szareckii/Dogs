package com.zareckii.dogs.ui.breeddetail.models

import androidx.compose.runtime.Immutable
import com.zareckii.dogs.ui.breeds.models.ImageUi

sealed class BreedDetailAction {
    data object SnackbarShow : BreedDetailAction()
    data object None : BreedDetailAction()
}

@Immutable
data class BreedDetailViewState(
    val breedDetailAction: BreedDetailAction = BreedDetailAction.None,
    val isLoading: Boolean = true,
    val breedName: String = "",
    val currentImage: ImageUi? = null,
    val images: List<ImageUi> = emptyList(),
    val snackbarMessage: String = ""
)
