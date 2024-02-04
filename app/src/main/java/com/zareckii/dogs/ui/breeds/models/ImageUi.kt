package com.zareckii.dogs.ui.breeds.models

import androidx.compose.runtime.Immutable

@Immutable
data class ImageUi(
    val imageUrl: String,
    val isFavorite: Boolean = false
)
