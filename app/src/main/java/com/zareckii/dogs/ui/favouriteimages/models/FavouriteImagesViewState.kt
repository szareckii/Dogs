package com.zareckii.dogs.ui.favouriteimages.models

data class FavouriteImagesViewState (
    val isLoading: Boolean = true,
    val images: List<String> = emptyList()
)
