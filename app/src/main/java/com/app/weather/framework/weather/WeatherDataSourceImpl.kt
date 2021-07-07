package com.app.weather.framework.weather

import com.app.core.data.datasource.weather.WeatherDataSource
import com.app.core.data.network.api.weather.WeatherApi
import com.app.core.data.repository.BaseDataSource
import com.app.core.domain.ResultWrapper
import com.app.core.domain.weather.ForecastResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDataSourceImpl @Inject constructor(private val weatherApi: WeatherApi) :
    BaseDataSource(), WeatherDataSource {

    override suspend fun forecast(latLong: String): Flow<ResultWrapper<ForecastResponse>> {
        return flowOnIO {
            weatherApi.forecast("d9634b89467748b8b4261455210107", latLong, "no", 6)
        }
    }
}