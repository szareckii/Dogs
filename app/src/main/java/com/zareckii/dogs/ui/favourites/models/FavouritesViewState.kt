package com.zareckii.dogs.ui.favourites.models

import androidx.compose.runtime.Immutable
import com.zareckii.dogs.ui.breeds.models.BreedUi

@Immutable
data class FavouritesViewState(
    val isLoading: Boolean = true,
    val breeds: List<BreedUi> = emptyList(),
)
