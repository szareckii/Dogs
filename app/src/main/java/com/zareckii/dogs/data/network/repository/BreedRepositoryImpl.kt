package com.zareckii.dogs.data.network.repository

import com.zareckii.dogs.data.mapper.BreedMapper
import com.zareckii.dogs.data.network.datasource.LocalDataSource
import com.zareckii.dogs.data.network.datasource.RemoteDataSource
import com.zareckii.dogs.domain.repository.BreedRepository
import com.zareckii.dogs.ui.breeds.models.BreedUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedRepositoryImpl @Inject constructor(
    private val dataSourceRemote: RemoteDataSource,
    private val dataSourceLocal: LocalDataSource,
    private val breedMapper: BreedMapper,
) : BreedRepository {

    override suspend fun getBreeds(): List<BreedUi> =
        breedMapper.mapBreadsApiToUI(dataSourceRemote.getBreeds())

    override fun getBreedsDb(): Flow<List<BreedUi>> =
        dataSourceLocal.getAllBreeds().map {
            breedMapper.mapBreadsDbToUi(it)
        }

    override suspend fun getBreedDb(breed: String): BreedUi =
        breedMapper.mapBreadDbToUi(dataSourceLocal.getBreed(breed))

    override suspend fun fetchBreedsDb() {
        val breedsApi = dataSourceRemote.getBreeds()
        val breedsDb = breedMapper.mapBreadsApiToDb(breedsApi)
        dataSourceLocal.insertAllBreeds(breedsDb)
    }

    override suspend fun searchBreedAsc(breed: String): List<BreedUi> =
        breedMapper.mapBreadsDbToUi(dataSourceLocal.searchBreedAsc(breed))

    override suspend fun searchBreedDesc(breed: String): List<BreedUi> =
        breedMapper.mapBreadsDbToUi(dataSourceLocal.searchBreedDesc(breed))

}
