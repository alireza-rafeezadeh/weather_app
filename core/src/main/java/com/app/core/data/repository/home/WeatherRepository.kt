package com.app.core.data.repository.home

import com.app.core.data.repository.ResultWrapper
import com.app.core.domain.ForecastResponse
import kotlinx.coroutines.flow.Flow


interface WeatherRepository {
    suspend fun forecast(latLong: String) : Flow<ResultWrapper<ForecastResponse>>
}