package com.zareckii.dogs.domain

import com.zareckii.dogs.data.Repository
import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.ui.breeds.models.BreedUi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBreedDbUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, BreedUi>(dispatcher) {
    override suspend fun execute(parameters: String): BreedUi {
        return repository.getBreedDb(parameters)
    }
}