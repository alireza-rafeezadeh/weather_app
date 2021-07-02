package com.app.core.data.repository

import com.app.core.domain.ForecastResponse
import retrofit2.Response


interface HomeRepository {
    fun forecast() : Response<ForecastResponse>
}