package com.zareckii.dogs.ui.breeds.models

import androidx.compose.runtime.Immutable

@Immutable
data class BreedsViewState(
    val isLoading: Boolean = false,
    val breeds: List<BreedUi> = emptyList()
)
