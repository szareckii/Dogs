package com.zareckii.dogs.data

import com.zareckii.dogs.ui.breeds.models.BreedUi
import com.zareckii.dogs.ui.breeds.models.ImageUI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedRepositoryImpl @Inject constructor(
    private val dataSource: BreedDataSource,
    private val breedMapper: BreedMapper
) : BreedRepository {

    override suspend fun getBreeds(): List<BreedUi> =
        breedMapper.mapBreadsAPItoUI(dataSource.getBreeds())

    override suspend fun getImageRandom(breed: String): ImageUI =
        breedMapper.mapImageRandomAPItoUI(dataSource.getImageRandom(breed))

    override suspend fun getImages(breed: String): List<ImageUI> =
        breedMapper.mapImagesAPItoUI(dataSource.getImages(breed))
}
