package com.zareckii.dogs.data

import retrofit2.http.GET
import retrofit2.http.Path

interface BreedService {

    @GET("breeds/list/all")
    suspend fun getBreeds(): BreedsApi

    @GET("breed/{breed}/images/random")
    suspend fun getImageRandom(@Path("breed") breed: String): ImageRandomApi

    @GET("breed/{breed}/images")
    suspend fun getImages(@Path("breed") breed: String): ImagesApi

}