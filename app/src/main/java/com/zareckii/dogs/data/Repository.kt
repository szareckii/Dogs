package com.zareckii.dogs.data

import com.zareckii.dogs.ui.breeds.models.BreedUi
import com.zareckii.dogs.ui.breeds.models.ImageUi
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getBreeds(): List<BreedUi>
    fun getBreedsDb(): Flow<List<BreedUi>>
    suspend fun getBreedDb(breed: String): BreedUi
    suspend fun fetchBreedsDb()

    suspend fun getImageRandom(breed: String): ImageUi
    fun getImagesDb(breed: String): Flow<List<ImageUi>>
    suspend fun fetchImagesDb(breed: String)
    suspend fun updateImageFavorite(imageUrl: String, isFavorite: Boolean)
    suspend fun getImage(imageUrl: String): ImageUi

}