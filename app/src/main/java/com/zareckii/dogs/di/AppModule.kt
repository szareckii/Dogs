package com.zareckii.dogs.di

import android.content.Context
import com.zareckii.dogs.data.mapper.BreedMapper
import com.zareckii.dogs.data.mapper.ImageMapper
import com.zareckii.dogs.utils.ManageResources
import com.zareckii.dogs.utils.Now
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    fun provideManageResources(
        @ApplicationContext context: Context
    ): ManageResources = ManageResources.Base(context)

}