package com.zareckii.dogs.domain.usecase.imageusecase

import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.domain.repository.ImageRepository
import com.zareckii.dogs.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteImagesUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, Flow<List<String>>>(dispatcher) {
    override suspend fun execute(parameters: String): Flow<List<String>> =
        imageRepository.getFavoriteImages(parameters)
}