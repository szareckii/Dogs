package com.zareckii.dogs.domain.usecase.imageusecase

import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.domain.repository.ImageRepository
import com.zareckii.dogs.domain.usecase.UseCase
import com.zareckii.dogs.ui.breeds.models.BreedUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteBreedsUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Flow<List<BreedUi>>>(dispatcher) {
    override suspend fun execute(parameters: Unit): Flow<List<BreedUi>> =
        imageRepository.getFavoriteBreeds()
}