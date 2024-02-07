package com.zareckii.dogs.domain.usecase.breedusecase

import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.domain.repository.BreedRepository
import com.zareckii.dogs.domain.usecase.UseCase
import com.zareckii.dogs.ui.breeds.models.BreedUi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(
    private val imageRepository: BreedRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, List<BreedUi>>(dispatcher) {
    override suspend fun execute(parameters: Unit): List<BreedUi> =
        imageRepository.getBreeds()
}