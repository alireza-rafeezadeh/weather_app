package com.app.core.data.repository.weather

import com.app.core.domain.weather.ForecastResponse
import com.app.core.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun forecast(latLong: String) : Flow<ResultWrapper<ForecastResponse>>
}