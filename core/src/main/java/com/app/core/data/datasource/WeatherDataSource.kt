package com.app.core.data.datasource

import com.app.core.domain.ForecastResponse
import retrofit2.Response


interface WeatherDataSource {
    suspend fun forecast() : Response<ForecastResponse>
}