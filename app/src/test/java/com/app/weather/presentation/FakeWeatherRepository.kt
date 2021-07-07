package com.app.weather.presentation

import com.app.core.data.repository.weather.WeatherRepository
import com.app.core.domain.*
import com.app.core.domain.weather.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by alirezarafeezadeh on 7/2/21.
 */

class FakeWeatherRepository : WeatherRepository {
    override suspend fun forecast(latLong: String): Flow<ResultWrapper<ForecastResponse>> = flow {

        val current = Current(
            1, Condition(0, "", ""), 1.0, 1.0, 1.0,
            1.0, 1, 1, "", 1, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1, "", 1.0, 1.0
        )

        val location = Location("", 1.0, "", 1, 1.0, "",
        "", "")

//        ForecastResponse(current, Forecast(emptyList()), location)
        emit(ResultWrapper.Success(ForecastResponse(current, Forecast(emptyList()), location)))
    }
}
