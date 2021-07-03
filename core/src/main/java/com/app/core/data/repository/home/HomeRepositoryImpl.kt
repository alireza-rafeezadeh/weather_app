package com.app.core.data.repository.home

import com.app.core.data.datasource.HomeDataSource
import com.app.core.data.repository.home.HomeRepository
import com.app.core.domain.ForecastResponse
import retrofit2.Response
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(val homeDataSource: HomeDataSource) : HomeRepository {
    override fun forecast(): Response<ForecastResponse> {
        TODO("Not yet implemented")
    }

}