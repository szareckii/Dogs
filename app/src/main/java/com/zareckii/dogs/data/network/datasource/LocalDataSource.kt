package com.zareckii.dogs.data.network.datasource

import com.zareckii.dogs.data.db.entity.BreedEntity
import com.zareckii.dogs.data.db.entity.ImageEntity
import com.zareckii.dogs.data.db.entity.ImageLastFavorite
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllBreeds(): Flow<List<BreedEntity>>
    suspend fun getBreed(breed: String): BreedEntity
    suspend fun insertAllBreeds(breeds: List<BreedEntity>)

    fun getImages(breed: String): Flow<List<ImageEntity>>
    suspend fun insertAllImages(images: List<ImageEntity>)
    suspend fun updateImageFavorite(imageUrl: String, isFavorite: Boolean, added: Long?)
    suspend fun getImage(imageUrl: String): ImageEntity
    fun getFavoriteBreeds(): Flow<List<ImageLastFavorite>>
    fun getFavoriteImages(breed: String): Flow<List<String>>
    suspend fun searchBreedAsc(breed: String): List<BreedEntity>
    suspend fun searchBreedDesc(breed: String): List<BreedEntity>
}
