package com.zareckii.dogs.domain

import com.zareckii.dogs.data.Repository
import com.zareckii.dogs.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchImagesDbUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, Unit>(dispatcher) {
    override suspend fun execute(parameters: String) {
        repository.fetchImagesDb(parameters)
    }
}