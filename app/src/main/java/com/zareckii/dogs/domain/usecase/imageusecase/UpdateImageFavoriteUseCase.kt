package com.zareckii.dogs.domain.usecase.imageusecase

import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.domain.model.ImageFavorite
import com.zareckii.dogs.domain.repository.ImageRepository
import com.zareckii.dogs.domain.usecase.UseCase
import com.zareckii.dogs.utils.Now
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UpdateImageFavoriteUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val now: Now
) : UseCase<ImageFavorite, Unit>(dispatcher) {
    override suspend fun execute(parameters: ImageFavorite) {
        val time = if (parameters.isFavorite) now.time() else null
        imageRepository.updateImageFavorite(parameters.imageUrl, parameters.isFavorite, time)
    }
}