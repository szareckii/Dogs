package com.zareckii.dogs.domain.usecase.breedusecase

import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.domain.repository.BreedRepository
import com.zareckii.dogs.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchBreedsDbUseCase @Inject constructor(
    private val imageRepository: BreedRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(parameters: Unit) {
        imageRepository.fetchBreedsDb()
    }
}