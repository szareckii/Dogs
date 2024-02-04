package com.zareckii.dogs.data

import com.zareckii.dogs.db.BreedEntity
import com.zareckii.dogs.db.BreedsDao
import com.zareckii.dogs.db.ImageEntity
import com.zareckii.dogs.db.ImagesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val breedsDao: BreedsDao,
    private val imagesDao: ImagesDao
) : LocalDataSource {

    override fun getAllBreeds(): Flow<List<BreedEntity>> =
        breedsDao.getAllBreeds()

    override suspend fun getBreed(breed: String): BreedEntity =
        breedsDao.getBreed(breed)

    override fun getFavoriteBreeds(isFavorite: Boolean): Flow<List<BreedEntity>> =
        breedsDao.getFavoriteBreeds(isFavorite)

    override suspend fun insertAllBreeds(breeds: List<BreedEntity>) =
        breedsDao.insertAllBreeds(breeds)

    override fun getImages(breed: String): Flow<List<ImageEntity>> =
        imagesDao.getImages(breed)

    override suspend fun insertAllImages(images: List<ImageEntity>) =
        imagesDao.insertAllImages(images)

    override suspend fun updateImageFavorite(imageUrl: String, isFavorite: Boolean) =
        imagesDao.updateImageFavorite(imageUrl, isFavorite)

    override suspend fun getImage(imageUrl: String): ImageEntity =
        imagesDao.getImage(imageUrl)
}
