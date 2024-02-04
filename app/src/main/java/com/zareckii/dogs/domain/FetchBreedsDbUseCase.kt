package com.zareckii.dogs.domain

import com.zareckii.dogs.data.Repository
import com.zareckii.dogs.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchBreedsDbUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(parameters: Unit) {
        repository.fetchBreedsDb()
    }
}