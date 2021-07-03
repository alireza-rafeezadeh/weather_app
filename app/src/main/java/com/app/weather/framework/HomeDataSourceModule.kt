package com.app.weather.framework

import com.app.core.data.datasource.HomeDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class HomeDataSourceModule {

    @Singleton
    @Binds
    abstract fun provideHomeDataSource(dataSource : HomeDataSourceImpl) : HomeDataSource
}