package com.app.core.data.repository.home

import com.app.core.data.repository.ResultWrapper
import com.app.core.domain.ForecastResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface WeatherRepository {
    suspend fun forecast() : Flow<ResultWrapper<ForecastResponse>>
}