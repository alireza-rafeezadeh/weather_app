package com.app.core.data.repository

import com.app.core.domain.ForecastResponse
import retrofit2.Response
import javax.inject.Inject


class HomeRepositoryImp @Inject constructor() : HomeRepository {
    override fun forecast(): Response<ForecastResponse> {
        TODO("Not yet implemented")
    }

}