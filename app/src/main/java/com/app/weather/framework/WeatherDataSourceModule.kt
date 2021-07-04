package com.app.weather.framework

import com.app.core.data.datasource.WeatherDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherDataSourceModule {

    @Singleton
    @Binds
    abstract fun provideWeatherDataSource(dataSource : WeatherDataSourceImpl) : WeatherDataSource
}