package com.zareckii.dogs.domain.usecase.breedusecase

import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.domain.repository.BreedRepository
import com.zareckii.dogs.domain.usecase.UseCase
import com.zareckii.dogs.ui.breeds.models.BreedUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBreedsDbUseCase @Inject constructor(
    private val breedRepository: BreedRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Flow<List<BreedUi>>>(dispatcher) {
    override suspend fun execute(parameters: Unit): Flow<List<BreedUi>> {
        return breedRepository.getBreedsDb()
    }
}