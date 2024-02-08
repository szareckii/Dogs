package com.zareckii.dogs.ui.breeds.models

import androidx.compose.runtime.Immutable

@Immutable
data class BreedUi(
    val breedName: String = "",
    val subBreeds: List<String> = emptyList(),
    val imageUrl: String = ""
)
