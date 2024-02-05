package com.zareckii.dogs.domain

import com.zareckii.dogs.data.Repository
import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.utils.Now
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UpdateImageFavoriteUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val now: Now
) : UseCase<ImageFavorite, Unit>(dispatcher) {
    override suspend fun execute(parameters: ImageFavorite) {
        val time = if ( parameters.isFavorite) now.time() else null
        repository.updateImageFavorite(parameters.imageUrl, parameters.isFavorite, time)
    }
}