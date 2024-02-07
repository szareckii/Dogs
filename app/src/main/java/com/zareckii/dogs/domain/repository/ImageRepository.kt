package com.zareckii.dogs.domain.repository

import com.zareckii.dogs.ui.breeds.models.BreedUi
import com.zareckii.dogs.ui.breeds.models.ImageUi
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun getImageRandom(breed: String): ImageUi
    fun getImagesDb(breed: String): Flow<List<ImageUi>>
    suspend fun fetchImagesDb(breed: String)
    suspend fun updateImageFavorite(imageUrl: String, isFavorite: Boolean, added: Long?)
    suspend fun getImage(imageUrl: String): ImageUi
    fun getFavoriteBreeds(): Flow<List<BreedUi>>
    fun getFavoriteImages(breed: String): Flow<List<String>>
}
