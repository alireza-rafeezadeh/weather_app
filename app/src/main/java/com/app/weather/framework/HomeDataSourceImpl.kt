package com.app.weather.framework

import com.app.core.data.datasource.HomeDataSource
import com.app.core.data.network.api.weather.WeatherApi
import javax.inject.Inject


class HomeDataSourceImpl @Inject constructor(private val weatherApi: WeatherApi) : HomeDataSource{
    override suspend fun forecast() {
//        weatherApi.forecast("","","",1)
    }
}