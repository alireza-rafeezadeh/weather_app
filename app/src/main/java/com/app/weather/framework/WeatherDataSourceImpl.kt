package com.app.weather.framework

import com.app.core.data.datasource.WeatherDataSource
import com.app.core.data.network.api.weather.WeatherApi
import com.app.core.data.repository.BaseDataSource
import com.app.core.data.repository.ResultWrapper
import com.app.core.domain.ForecastResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


class WeatherDataSourceImpl @Inject constructor(private val weatherApi: WeatherApi) :
    BaseDataSource(), WeatherDataSource{

    override suspend fun forecast() : Flow<ResultWrapper<ForecastResponse>> {
//        weatherApi.forecast("","","",1)
        return flowOnIO {
            weatherApi.forecast("","","",14)
        }

    }

}