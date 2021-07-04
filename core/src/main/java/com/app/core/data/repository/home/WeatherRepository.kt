package com.app.core.data.repository.home

import com.app.core.domain.ForecastResponse
import retrofit2.Response


interface WeatherRepository {
    suspend fun forecast() : Response<ForecastResponse>
}