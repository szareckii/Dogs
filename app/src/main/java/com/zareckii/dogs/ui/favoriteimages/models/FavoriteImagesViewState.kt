package com.zareckii.dogs.ui.favoriteimages.models

data class FavoriteImagesViewState (
    val isLoading: Boolean = true,
    val images: List<String> = emptyList()
)
