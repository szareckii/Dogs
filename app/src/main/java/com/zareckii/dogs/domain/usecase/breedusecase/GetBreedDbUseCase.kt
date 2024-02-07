package com.zareckii.dogs.domain.usecase.breedusecase

import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.domain.repository.BreedRepository
import com.zareckii.dogs.domain.usecase.UseCase
import com.zareckii.dogs.ui.breeds.models.BreedUi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBreedDbUseCase @Inject constructor(
    private val imageRepository: BreedRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, BreedUi>(dispatcher) {
    override suspend fun execute(parameters: String): BreedUi {
        return imageRepository.getBreedDb(parameters)
    }
}