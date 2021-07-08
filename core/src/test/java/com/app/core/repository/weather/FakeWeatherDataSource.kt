package com.app.core.repository.weather

import com.app.core.data.datasource.weather.WeatherDataSource
import com.app.core.domain.ResultWrapper
import com.app.core.domain.weather.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWeatherDataSource : WeatherDataSource {

    override suspend fun forecast(latLong : String): Flow<ResultWrapper<ForecastResponse>> = flow {
        val current = Current(
            1, Condition(0,"",""), 1.0, 1.0, 1.0,
            1.0, 1, 1, "", 1, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1, "",1.0,1.0
        )

        val location = Location("",1.0,"",1,1.0,"",
            "","")
        ForecastResponse(current, Forecast(emptyList()), location)
    }
}