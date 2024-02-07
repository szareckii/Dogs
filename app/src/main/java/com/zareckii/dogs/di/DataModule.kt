package com.zareckii.dogs.di

import com.zareckii.dogs.data.network.repository.BreedRepositoryImpl
import com.zareckii.dogs.domain.repository.ImageRepository
import com.zareckii.dogs.data.network.repository.ImageRepositoryImpl
import com.zareckii.dogs.domain.repository.BreedRepository
import com.zareckii.dogs.utils.ConnectivityManagerNetworkMonitor
import com.zareckii.dogs.utils.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindBreedRepository(
        dogRepository: BreedRepositoryImpl
    ): BreedRepository

    @Binds
    fun bindImageRepository(
        dogRepository: ImageRepositoryImpl
    ): ImageRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}
