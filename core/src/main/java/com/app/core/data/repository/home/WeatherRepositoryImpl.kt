package com.app.core.data.repository.home

import com.app.core.data.datasource.WeatherDataSource
import com.app.core.domain.ForecastResponse
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(private val weatherDataSource: WeatherDataSource) : WeatherRepository {
    override suspend fun forecast(): Response<ForecastResponse> {

        weatherDataSource.forecast()
        return Response.success(null)
    }

}