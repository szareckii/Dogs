package com.zareckii.dogs.ui.breed.models

import androidx.compose.runtime.Immutable
import com.zareckii.dogs.ui.breeds.models.ImageUI

@Immutable
data class BreedViewState(
    val isLoading: Boolean = true,
    val name: String = "",
    val currentImage: ImageUI? = null,
    val images: List<ImageUI> = emptyList()
)
