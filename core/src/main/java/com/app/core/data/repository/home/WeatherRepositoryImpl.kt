package com.app.core.data.repository.home

import com.app.core.data.datasource.WeatherDataSource
import com.app.core.domain.ForecastResponse
import retrofit2.Response
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(private val weatherDataSource: WeatherDataSource) : WeatherRepository {
    override fun forecast(): Response<ForecastResponse> {

    }

}