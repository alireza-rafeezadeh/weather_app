package com.app.weather.presentation

import com.app.core.data.repository.home.HomeRepository
import com.app.core.domain.*
import retrofit2.Response

/**
 * Created by alirezarafeezadeh on 7/2/21.
 */

class FakeHomeRepository : HomeRepository {
    override fun forecast(): Response<ForecastResponse> {

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