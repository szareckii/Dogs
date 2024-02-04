package com.zareckii.dogs.di

import com.zareckii.dogs.data.Repository
import com.zareckii.dogs.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindDogRepository(
        dogRepository: RepositoryImpl
    ): Repository
}
