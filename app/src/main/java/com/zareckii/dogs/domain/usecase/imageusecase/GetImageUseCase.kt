package com.zareckii.dogs.domain.usecase.imageusecase

import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.domain.repository.ImageRepository
import com.zareckii.dogs.domain.usecase.UseCase
import com.zareckii.dogs.ui.breeds.models.ImageUi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, ImageUi>(dispatcher) {
    override suspend fun execute(parameters: String): ImageUi =
        imageRepository.getImage(parameters)
}