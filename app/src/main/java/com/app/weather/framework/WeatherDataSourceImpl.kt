package com.app.weather.framework

import com.app.core.data.datasource.WeatherDataSource
import com.app.core.data.network.api.weather.WeatherApi
import com.app.core.domain.ForecastResponse
import retrofit2.Response
import javax.inject.Inject


class WeatherDataSourceImpl @Inject constructor(private val weatherApi: WeatherApi) : WeatherDataSource{
    override suspend fun forecast() : Response<ForecastResponse> {
//        weatherApi.forecast("","","",1)

        return Response.success(null)
    }
}