package com.zareckii.dogs.domain

import com.zareckii.dogs.data.Repository
import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.ui.breeds.models.ImageUi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, ImageUi>(dispatcher) {
    override suspend fun execute(parameters: String): ImageUi =
        repository.getImage(parameters)
}