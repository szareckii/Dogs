package com.zareckii.dogs.data.mapper

import com.zareckii.dogs.data.network.model.ImageRandomApi
import com.zareckii.dogs.data.network.model.ImagesApi
import com.zareckii.dogs.data.db.entity.ImageEntity
import com.zareckii.dogs.ui.breeds.models.ImageUi

interface ImageMapper {

    fun mapImagesApiToDb(breed: String, images: ImagesApi): List<ImageEntity>

    fun mapImageDbToUI(image: ImageEntity): ImageUi

    fun mapImagesDbToUI(images: List<ImageEntity>): List<ImageUi>

    fun mapImagesUiToDb(breed: String, images: List<ImageUi>): List<ImageEntity>

    fun mapImageRandomApiToUI(image: ImageRandomApi): ImageUi

    class Base : ImageMapper {

        override fun mapImagesApiToDb(breed: String, images: ImagesApi): List<ImageEntity> =
            images.message.map { image ->
                ImageEntity(breedName = breed, imageUrl = image, isFavorite = false)
            }

        override fun mapImageDbToUI(image: ImageEntity): ImageUi =
            ImageUi(imageUrl = image.imageUrl, isFavorite = image.isFavorite)

        override fun mapImagesDbToUI(images: List<ImageEntity>): List<ImageUi> =
            images.map { image ->
                ImageUi(imageUrl = image.imageUrl, isFavorite = image.isFavorite)
            }

        override fun mapImagesUiToDb(breed: String, images: List<ImageUi>): List<ImageEntity> =
            images.map { image ->
                ImageEntity(
                    breedName = breed,
                    imageUrl = image.imageUrl,
                    isFavorite = image.isFavorite
                )
            }

        override fun mapImageRandomApiToUI(image: ImageRandomApi): ImageUi =
            ImageUi(image.message)
    }

}
