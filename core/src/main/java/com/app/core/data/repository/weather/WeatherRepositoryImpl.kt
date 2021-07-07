package com.app.core.data.repository.weather

import com.app.core.data.datasource.weather.WeatherDataSource
import com.app.core.domain.weather.ForecastResponse
import com.app.core.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {
    override suspend fun forecast(latLong: String): Flow<ResultWrapper<ForecastResponse>> {
        return weatherDataSource.forecast(latLong)
    }
}