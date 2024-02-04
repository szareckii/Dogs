package com.zareckii.dogs.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zareckii.dogs.data.BreedDataSource
import com.zareckii.dogs.data.BreedNetworkDataSource
import com.zareckii.dogs.data.BreedService
import com.zareckii.dogs.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideBaseUrl(): String = "https://dog.ceo/api/"

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().serializeNulls().setLenient().create()


    @Provides
    @Singleton
    fun provideGsonConverterFactory(
        gson: Gson
    ): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @Singleton
    fun provideAppService(
        retrofit: Retrofit
    ): BreedService =
        retrofit.create(BreedService::class.java)

    @Provides
    @Singleton
    fun provideCameraNetworkDataSource(
        cameraService: BreedService
    ): BreedDataSource =
        BreedNetworkDataSource(cameraService)

}
