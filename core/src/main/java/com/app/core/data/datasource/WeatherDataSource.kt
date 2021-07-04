package com.app.core.data.datasource

import com.app.core.data.repository.ResultWrapper
import com.app.core.domain.ForecastResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface WeatherDataSource {
    suspend fun forecast() : Flow<ResultWrapper<ForecastResponse>>
}