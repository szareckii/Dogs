package com.zareckii.dogs.domain

import com.zareckii.dogs.data.Repository
import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.ui.breeds.models.BreedUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBreedsDbUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Flow<List<BreedUi>>>(dispatcher) {
    override suspend fun execute(parameters: Unit): Flow<List<BreedUi>> {
        return repository.getBreedsDb()
    }
}