package com.app.core.repository.util

import com.app.core.domain.weather.*

object ForecastMockData {
    fun forecastFakeResponse(): ForecastResponse {
        val current = Current(
            1, Condition(0, "", ""), 1.0, 1.0, 1.0,
            1.0, 1, 1, "", 1, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1, "", 1.0, 1.0
        )

        val location = Location(
            "", 1.0, "", 1, 1.0, "",
            "", ""
        )
        return ForecastResponse(current, Forecast(emptyList()), location)
    }
}