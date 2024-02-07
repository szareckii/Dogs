package com.zareckii.dogs.domain.usecase.imageusecase

import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.domain.repository.ImageRepository
import com.zareckii.dogs.domain.usecase.UseCase
import com.zareckii.dogs.ui.breeds.models.ImageUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, Flow<List<ImageUi>>>(dispatcher) {
    override suspend fun execute(parameters: String): Flow<List<ImageUi>> =
        imageRepository.getImagesDb(parameters)
}