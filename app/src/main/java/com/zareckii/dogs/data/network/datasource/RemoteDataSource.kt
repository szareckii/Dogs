package com.zareckii.dogs.data.network.datasource

import com.zareckii.dogs.data.network.model.BreedsApi
import com.zareckii.dogs.data.network.model.ImageRandomApi
import com.zareckii.dogs.data.network.model.ImagesApi

interface RemoteDataSource {
    suspend fun getBreeds(): BreedsApi
    suspend fun getImageRandom(breed: String): ImageRandomApi
    suspend fun getImages(breed: String): ImagesApi
}
