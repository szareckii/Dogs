package com.zareckii.dogs.data.mapper

import com.zareckii.dogs.data.network.model.BreedsApi
import com.zareckii.dogs.data.db.entity.BreedEntity
import com.zareckii.dogs.data.db.entity.ImageLastFavorite
import com.zareckii.dogs.ui.breeds.models.BreedUi

interface BreedMapper {

    fun mapBreadsApiToUI(breeds: BreedsApi): List<BreedUi>

    fun mapBreadsDbToUi(breeds: List<BreedEntity>): List<BreedUi>

    fun mapBreadDbToUi(breed: BreedEntity): BreedUi

    fun mapBreadsApiToDb(breeds: BreedsApi): List<BreedEntity>

    fun mapImageLstFavoriteToUI(names: List<ImageLastFavorite>): List<BreedUi>

    class Base : BreedMapper {
        override fun mapBreadsApiToUI(breeds: BreedsApi): List<BreedUi> =
            breeds.message.map { breed ->
                BreedUi(breedName = breed.key, subBreeds = breed.value)
            }

        override fun mapBreadsDbToUi(breeds: List<BreedEntity>): List<BreedUi> =
            breeds.map { breedDb ->
                BreedUi(
                    breedName = breedDb.breedName,
                    subBreeds = emptyList()
                )
            }

        override fun mapBreadDbToUi(breed: BreedEntity): BreedUi =
            BreedUi(
                breedName = breed.breedName,
                subBreeds = emptyList()
            )

        override fun mapBreadsApiToDb(breeds: BreedsApi): List<BreedEntity> =
            breeds.message.map { breedApi ->
                BreedEntity(breedName = breedApi.key)
            }

        override fun mapImageLstFavoriteToUI(names: List<ImageLastFavorite>): List<BreedUi> =
            names.map { breed ->
                BreedUi(
                    breedName = breed.breedName,
                    subBreeds = emptyList(),
                    imageUrl = breed.imageUrl
                )
            }

    }
}
