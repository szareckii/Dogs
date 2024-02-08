package com.zareckii.dogs.domain.repository

import com.zareckii.dogs.ui.breeds.models.BreedUi
import kotlinx.coroutines.flow.Flow

interface BreedRepository {

    suspend fun getBreeds(): List<BreedUi>
    fun getBreedsDb(): Flow<List<BreedUi>>
    suspend fun getBreedDb(breed: String): BreedUi
    suspend fun fetchBreedsDb()
    suspend fun searchBreedDesc(breed: String): List<BreedUi>
    suspend fun searchBreedAsc(breed: String): List<BreedUi>

}
