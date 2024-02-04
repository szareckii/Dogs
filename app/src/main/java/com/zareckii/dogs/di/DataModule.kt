package com.zareckii.dogs.di

import com.zareckii.dogs.data.BreedRepository
import com.zareckii.dogs.data.BreedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindDogRepository(
        dogRepository: BreedRepositoryImpl
    ): BreedRepository
}
