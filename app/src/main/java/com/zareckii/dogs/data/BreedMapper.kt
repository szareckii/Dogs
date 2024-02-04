package com.zareckii.dogs.data

import com.zareckii.dogs.db.BreedEntity
import com.zareckii.dogs.ui.breeds.models.BreedUi

interface BreedMapper {

    fun mapBreadsApiToUI(breeds: BreedsApi): List<BreedUi>

    fun mapBreadsDbToUi(breeds: List<BreedEntity>): List<BreedUi>

    fun mapBreadDbToUi(breed: BreedEntity): BreedUi

    fun mapBreadsApiToDb(breeds: BreedsApi): List<BreedEntity>

    class Base : BreedMapper {
        override fun mapBreadsApiToUI(breeds: BreedsApi): List<BreedUi> =
            breeds.message.map { breed ->
                BreedUi(breedName = breed.key, subBreeds = breed.value, isFavorite = false)
            }

        override fun mapBreadsDbToUi(breeds: List<BreedEntity>): List<BreedUi> =
            breeds.map { breedDb ->
                BreedUi(
                    breedName = breedDb.breedName,
                    subBreeds = emptyList(),
                    isFavorite = breedDb.isFavorite
                )
            }

        override fun mapBreadDbToUi(breed: BreedEntity): BreedUi =
            BreedUi(
                breedName = breed.breedName,
                subBreeds = emptyList(),
                isFavorite = breed.isFavorite
            )

        override fun mapBreadsApiToDb(breeds: BreedsApi): List<BreedEntity> =
            breeds.message.map { breedApi ->
                BreedEntity(breedName = breedApi.key, isFavorite = false)
            }

    }
}
