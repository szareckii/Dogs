package com.zareckii.dogs.domain

import com.zareckii.dogs.data.Repository
import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.ui.breeds.models.ImageUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, Flow<List<ImageUi>>>(dispatcher) {
    override suspend fun execute(parameters: String): Flow<List<ImageUi>> =
        repository.getImagesDb(parameters)
}