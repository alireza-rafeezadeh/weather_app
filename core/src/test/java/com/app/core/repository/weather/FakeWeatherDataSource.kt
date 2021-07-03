package com.app.core.repository.weather

import com.app.core.data.datasource.WeatherDataSource
import com.app.core.domain.*
import retrofit2.Response

/**
 * Created by alirezarafeezadeh on 7/3/21.
 */

class FakeWeatherDataSource : WeatherDataSource {
    override suspend fun forecast(): Response<ForecastResponse> {

        val current = Current(
            1, Condition(0,"",""), 1.0, 1.0, 1.0,
            1.0, 1, 1, "", 1, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1, "",1.0,1.0
        )

        val location = Location("",1.0,"",1,1.0,"",
            "","")

        val resp = ForecastResponse(current, Forecast(emptyList()), location)


        return Response.success(resp)
    }
}