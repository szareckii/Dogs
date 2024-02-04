package com.zareckii.dogs.di

import com.zareckii.dogs.data.BreedMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideBreedMapper(): BreedMapper = BreedMapper.Base()
}