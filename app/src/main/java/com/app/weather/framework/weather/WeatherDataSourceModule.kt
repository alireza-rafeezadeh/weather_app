package com.app.weather.framework.weather

import com.app.core.data.datasource.weather.WeatherDataSource
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
    abstract fun provideWeatherDataSource(dataSource: WeatherDataSourceImpl): WeatherDataSource
}
