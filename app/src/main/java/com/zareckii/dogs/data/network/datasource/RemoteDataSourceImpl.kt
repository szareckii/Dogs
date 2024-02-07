package com.zareckii.dogs.data.network.datasource

import com.zareckii.dogs.data.network.BreedService
import com.zareckii.dogs.data.network.model.BreedsApi
import com.zareckii.dogs.data.network.model.ImageRandomApi
import com.zareckii.dogs.data.network.model.ImagesApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val service: BreedService
) : RemoteDataSource {

    override suspend fun getBreeds(): BreedsApi =
        service.getBreeds()

    override suspend fun getImageRandom(breed: String): ImageRandomApi =
        service.getImageRandom(breed)

    override suspend fun getImages(breed: String): ImagesApi =
        service.getImages(breed)

}
