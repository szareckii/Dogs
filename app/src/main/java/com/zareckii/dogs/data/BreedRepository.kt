package com.zareckii.dogs.data

import com.zareckii.dogs.ui.breeds.models.BreedUi
import com.zareckii.dogs.ui.breeds.models.ImageUI

interface BreedRepository {
    suspend fun getBreeds(): List<BreedUi>
    suspend fun getImageRandom(breed: String): ImageUI
    suspend fun getImages(breed: String): List<ImageUI>
}