package com.app.weather.presentation

import com.app.core.data.repository.ResultWrapper
import com.app.core.data.repository.home.WeatherRepository
import com.app.core.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * Created by alirezarafeezadeh on 7/2/21.
 */

class FakeWeatherRepository : WeatherRepository {
    override suspend fun forecast(): Flow<ResultWrapper<ForecastResponse>> = flow {

        val current = Current(
            1, Condition(0,"",""), 1.0, 1.0, 1.0,
            1.0, 1, 1, "", 1, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1, "",1.0,1.0
        )

        val location = Location("",1.0,"",1,1.0,"",
        "","")

//        ForecastResponse(current, Forecast(emptyList()), location)
        emit(ResultWrapper.Success(ForecastResponse(current, Forecast(emptyList()), location)))

    }
}