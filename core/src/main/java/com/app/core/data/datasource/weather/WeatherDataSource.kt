package com.app.core.data.datasource.weather

import com.app.core.domain.ResultWrapper
import com.app.core.domain.weather.ForecastResponse
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {
    suspend fun forecast(latLong : String) : Flow<ResultWrapper<ForecastResponse>>
}