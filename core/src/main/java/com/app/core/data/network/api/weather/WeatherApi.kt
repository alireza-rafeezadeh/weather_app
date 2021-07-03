package com.app.core.data.network.api.weather

import com.app.core.domain.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("forecast.json")
    suspend fun forecast(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("aqi") aqi: String,
        @Query("days") days: Int,
    ): Response<ForecastResponse>


}