package com.zareckii.dogs.di

import com.zareckii.dogs.data.db.AppDatabase
import com.zareckii.dogs.data.db.BreedsDao
import com.zareckii.dogs.data.db.ImagesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun provideBreadsDao(
        database: AppDatabase,
    ): BreedsDao = database.breedsDao()

    @Provides
    fun provideImagesDao(
        database: AppDatabase,
    ): ImagesDao = database.imageDao()

}
