package com.zareckii.dogs.ui.breed.models

import androidx.compose.runtime.Immutable
import com.zareckii.dogs.ui.breeds.models.ImageUi

@Immutable
data class BreedViewState(
    val isLoading: Boolean = true,
    val breedName: String = "",
    val currentImage: ImageUi? = null,
    val images: List<ImageUi> = emptyList()
)
