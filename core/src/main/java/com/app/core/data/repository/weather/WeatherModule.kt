package com.app.core.data.repository.weather

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherModule {

    @Singleton
    @Binds
    abstract fun provideHomeRepository(repository : WeatherRepositoryImpl) : WeatherRepository
}