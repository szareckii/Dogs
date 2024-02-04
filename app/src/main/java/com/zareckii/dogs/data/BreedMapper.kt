package com.zareckii.dogs.data

import com.zareckii.dogs.ui.breeds.models.BreedUi
import com.zareckii.dogs.ui.breeds.models.ImageUI

interface BreedMapper {

    fun mapBreadsAPItoUI(breeds: BreedsApi): List<BreedUi>

    fun mapImageRandomAPItoUI(image: ImageRandomApi): ImageUI

    fun mapImagesAPItoUI(images: ImagesApi): List<ImageUI>

    class Base : BreedMapper {
        override fun mapBreadsAPItoUI(breeds: BreedsApi): List<BreedUi> =
            breeds.message.map { breed ->
                BreedUi(breedName = breed.key, subBreeds = breed.value)
            }

        override fun mapImageRandomAPItoUI(image: ImageRandomApi): ImageUI =
            ImageUI(image.message)

        override fun mapImagesAPItoUI(images: ImagesApi): List<ImageUI> =
            images.message.map { imageUrl -> ImageUI(imageUrl) }
    }

}
