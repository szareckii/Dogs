package com.zareckii.dogs.data

import com.zareckii.dogs.db.BreedEntity
import com.zareckii.dogs.db.ImageEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllBreeds(): Flow<List<BreedEntity>>
    suspend fun getBreed(breed: String): BreedEntity
    fun getFavoriteBreeds(isFavorite: Boolean): Flow<List<BreedEntity>>
    suspend fun insertAllBreeds(breeds: List<BreedEntity>)

    fun getImages(breed: String): Flow<List<ImageEntity>>
    suspend fun insertAllImages(images: List<ImageEntity>)
    suspend fun updateImageFavorite(imageUrl: String, isFavorite: Boolean)
    suspend fun getImage(imageUrl: String): ImageEntity

}
