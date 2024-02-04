package com.zareckii.dogs.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedNetworkDataSource @Inject constructor(
    private val service: BreedService
) : BreedDataSource {

    override suspend fun getBreeds(): BreedsApi =
        service.getBreeds()

    override suspend fun getImageRandom(breed: String): ImageRandomApi =
        service.getImageRandom(breed)

    override suspend fun getImages(breed: String): ImagesApi =
        service.getImages(breed)

}
