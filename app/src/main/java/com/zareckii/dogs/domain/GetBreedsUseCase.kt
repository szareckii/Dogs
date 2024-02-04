package com.zareckii.dogs.domain

import com.zareckii.dogs.data.Repository
import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.ui.breeds.models.BreedUi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, List<BreedUi>>(dispatcher) {
    override suspend fun execute(parameters: Unit): List<BreedUi> =
        repository.getBreeds()
}