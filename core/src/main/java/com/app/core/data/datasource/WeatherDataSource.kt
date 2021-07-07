package com.app.core.data.datasource

import com.app.core.domain.ForecastResponse
import com.app.core.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {
    suspend fun forecast(latLong : String) : Flow<ResultWrapper<ForecastResponse>>
}