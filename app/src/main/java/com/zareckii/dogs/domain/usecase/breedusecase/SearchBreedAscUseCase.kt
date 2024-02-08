package com.zareckii.dogs.domain.usecase.breedusecase

import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.domain.repository.BreedRepository
import com.zareckii.dogs.domain.usecase.UseCase
import com.zareckii.dogs.ui.breeds.models.BreedUi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SearchBreedAscUseCase @Inject constructor(
    private val breedRepository: BreedRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, List<BreedUi>>(dispatcher) {
    override suspend fun execute(parameters: String): List<BreedUi> {
        return breedRepository.searchBreedAsc(parameters.lowercase())
    }
}