package com.zareckii.dogs.di

import com.zareckii.dogs.data.BreedMapper
import com.zareckii.dogs.data.ImageMapper
import com.zareckii.dogs.utils.Now
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

    @Provides
    @Singleton
    fun provideImageMapper(): ImageMapper = ImageMapper.Base()

    @Provides
    @Singleton
    fun provideNow(): Now = Now.Base()
}