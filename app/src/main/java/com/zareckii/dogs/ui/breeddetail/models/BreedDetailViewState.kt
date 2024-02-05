package com.zareckii.dogs.ui.breeddetail.models

import androidx.compose.runtime.Immutable
import com.zareckii.dogs.ui.breeds.models.ImageUi

@Immutable
data class BreedDetailViewState(
    val isLoading: Boolean = true,
    val breedName: String = "",
    val currentImage: ImageUi? = null,
    val images: List<ImageUi> = emptyList()
)
