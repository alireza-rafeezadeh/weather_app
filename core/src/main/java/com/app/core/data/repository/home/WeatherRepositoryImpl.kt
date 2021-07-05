package com.app.core.data.repository.home

import com.app.core.data.datasource.WeatherDataSource
import com.app.core.data.repository.ResultWrapper
import com.app.core.domain.ForecastResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(private val weatherDataSource: WeatherDataSource) : WeatherRepository {
    override suspend fun forecast(latLong: String): Flow<ResultWrapper<ForecastResponse>> {
        return weatherDataSource.forecast(latLong)
    }
}