package com.zareckii.dogs.data

interface BreedDataSource {
    suspend fun getBreeds(): BreedsApi
    suspend fun getImageRandom(breed: String): ImageRandomApi
    suspend fun getImages(breed: String): ImagesApi
}