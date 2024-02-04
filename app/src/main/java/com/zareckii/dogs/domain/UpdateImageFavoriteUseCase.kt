package com.zareckii.dogs.domain

import com.zareckii.dogs.data.Repository
import com.zareckii.dogs.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UpdateImageFavoriteUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<ImageFavorite, Unit>(dispatcher) {
    override suspend fun execute(parameters: ImageFavorite) {
        repository.updateImageFavorite(parameters.imageUrl, parameters.isFavorite)
    }
}