package com.zareckii.dogs.data.network.datasource

import com.zareckii.dogs.data.db.entity.BreedEntity
import com.zareckii.dogs.data.db.BreedsDao
import com.zareckii.dogs.data.db.entity.ImageEntity
import com.zareckii.dogs.data.db.entity.ImageLastFavorite
import com.zareckii.dogs.data.db.ImagesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val breedsDao: BreedsDao,
    private val imagesDao: ImagesDao
) : LocalDataSource {

    override fun getAllBreeds(): Flow<List<BreedEntity>> =
        try {
            breedsDao.getAllBreeds()
        } catch (_: Exception) {
            emptyFlow()
        }

    override suspend fun getBreed(breed: String): BreedEntity =
        breedsDao.getBreed(breed)

    override suspend fun insertAllBreeds(breeds: List<BreedEntity>) =
        try {
            breedsDao.insertAllBreeds(breeds)
        } catch (_: Exception) {
        }

    override fun getImages(breed: String): Flow<List<ImageEntity>> =
        try {
            imagesDao.getImages(breed)
        } catch (_: Exception) {
            emptyFlow()
        }

    override suspend fun insertAllImages(images: List<ImageEntity>) =
        try {
            imagesDao.insertAllImages(images)
        } catch (_: Exception) {
        }

    override suspend fun updateImageFavorite(imageUrl: String, isFavorite: Boolean, added: Long?) =
        try {
            imagesDao.updateImageFavorite(imageUrl, isFavorite, added)
        } catch (_: Exception) {
        }

    override suspend fun getImage(imageUrl: String): ImageEntity =
        imagesDao.getImage(imageUrl)

    override fun getFavoriteBreeds(): Flow<List<ImageLastFavorite>> =
        try {
            imagesDao.getFavoriteBreeds()
        } catch (_: Exception) {
            emptyFlow()
        }

    override fun getFavoriteImages(breed: String): Flow<List<String>> =
        try {
            imagesDao.getFavoriteImages(breed)
        } catch (_: Exception) {
            emptyFlow()
        }
}
