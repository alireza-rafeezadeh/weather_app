package com.app.weather.presentation.di

import com.app.weather.presentation.util.location.LocationHelper
import com.app.weather.presentation.util.location.LocationHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun provideLocationHelper(dataSource: LocationHelperImpl): LocationHelper
}