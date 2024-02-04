package com.zareckii.dogs.domain

import com.zareckii.dogs.data.BreedRepository
import com.zareckii.dogs.data.ImageRandomApi
import com.zareckii.dogs.data.ImagesApi
import com.zareckii.dogs.di.IoDispatcher
import com.zareckii.dogs.ui.breeds.models.BreedUi
import com.zareckii.dogs.ui.breeds.models.ImageUI
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repository: BreedRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<String, List<ImageUI>>(dispatcher) {
    override suspend fun execute(parameters: String): List<ImageUI> =
        repository.getImages(parameters)
}