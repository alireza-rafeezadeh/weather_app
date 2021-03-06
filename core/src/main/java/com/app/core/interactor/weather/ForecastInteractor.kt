package com.app.core.interactor.weather

import com.app.core.data.repository.weather.WeatherRepository
import javax.inject.Inject

class ForecastInteractor @Inject constructor(private val repository: WeatherRepository) {
    suspend fun forecast(latLong: String) = repository.forecast(latLong)
}