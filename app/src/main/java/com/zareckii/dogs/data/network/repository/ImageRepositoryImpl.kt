package com.zareckii.dogs.data.network.repository

import com.zareckii.dogs.data.network.datasource.LocalDataSource
import com.zareckii.dogs.data.network.datasource.RemoteDataSource
import com.zareckii.dogs.data.mapper.BreedMapper
import com.zareckii.dogs.data.mapper.ImageMapper
import com.zareckii.dogs.domain.repository.ImageRepository
import com.zareckii.dogs.ui.breeds.models.BreedUi
import com.zareckii.dogs.ui.breeds.models.ImageUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepositoryImpl @Inject constructor(
    private val dataSourceRemote: RemoteDataSource,
    private val dataSourceLocal: LocalDataSource,
    private val breedMapper: BreedMapper,
    private val imageMapper: ImageMapper
) : ImageRepository {

    override suspend fun getImageRandom(breed: String): ImageUi =
        imageMapper.mapImageRandomApiToUI(dataSourceRemote.getImageRandom(breed))

    override fun getImagesDb(breed: String): Flow<List<ImageUi>> =
        dataSourceLocal.getImages(breed).map {
            imageMapper.mapImagesDbToUI(it)
        }

    override suspend fun fetchImagesDb(breed: String) {
        val imagesApi = dataSourceRemote.getImages(breed)
        val imagesDb = imageMapper.mapImagesApiToDb(breed, imagesApi)
        dataSourceLocal.insertAllImages(imagesDb)
    }

    override suspend fun updateImageFavorite(imageUrl: String, isFavorite: Boolean, added: Long?) =
        dataSourceLocal.updateImageFavorite(imageUrl, isFavorite, added)

    override suspend fun getImage(imageUrl: String): ImageUi =
        imageMapper.mapImageDbToUI(dataSourceLocal.getImage(imageUrl))

    override fun getFavoriteBreeds(): Flow<List<BreedUi>> =
        dataSourceLocal.getFavoriteBreeds().map {
            breedMapper.mapImageLstFavoriteToUI(it)
        }

    override fun getFavoriteImages(breed: String): Flow<List<String>> =
        dataSourceLocal.getFavoriteImages(breed)

}
