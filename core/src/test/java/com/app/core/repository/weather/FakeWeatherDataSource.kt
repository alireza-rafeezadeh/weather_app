package com.app.core.repository.weather

import com.app.core.data.datasource.WeatherDataSource
import com.app.core.data.repository.ResultWrapper
import com.app.core.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import kotlin.system.measureTimeMillis

/**
 * Created by alirezarafeezadeh on 7/3/21.
 */

class FakeWeatherDataSource : WeatherDataSource {
//    override suspend fun forecast(): Flow<ResultWrapper<ForecastResponse>> {
//
//        val current = Current(
//            1, Condition(0,"",""), 1.0, 1.0, 1.0,
//            1.0, 1, 1, "", 1, 1.0,
//            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
//            1.0, 1.0, 1, "",1.0,1.0
//        )
//
//        val location = Location("",1.0,"",1,1.0,"",
//            "","")
//
//        val resp = ForecastResponse(current, Forecast(emptyList()), location)
//
//
//        flow<ResultWrapper<ForecastResponse>> {
//           Response.success(resp)
//        }
////        return Response.success(resp)
//    }

    override suspend fun forecast(): Flow<ResultWrapper<ForecastResponse>> = flow {
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