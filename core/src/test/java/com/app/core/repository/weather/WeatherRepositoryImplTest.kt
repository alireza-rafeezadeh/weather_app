package com.app.core.repository.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.MainCoroutineRule
import com.app.core.data.repository.ResultWrapper
import com.app.core.data.repository.home.WeatherRepositoryImpl
import com.app.core.domain.*
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var weatherRepository : WeatherRepositoryImpl

    @Before
    fun setUp() {
        weatherRepository = WeatherRepositoryImpl(FakeWeatherDataSource())
    }

    @Test
    fun forecast() = runBlocking {
        val response = weatherRepository.forecast("")
        val expectedResp = forecastFakeResponse()

        response.collect {
//            ResultWrapper.Success(expectedResp)
            assertThat(it).isEqualTo(ResultWrapper.Success(expectedResp))
        }
    }

    private fun forecastFakeResponse(): ForecastResponse {
        val current = Current(
            1, Condition(0,"",""), 1.0, 1.0, 1.0,
            1.0, 1, 1, "", 1, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1, "",1.0,1.0
        )

        val location = Location("",1.0,"",1,1.0,"",
            "","")

        return ForecastResponse(current, Forecast(emptyList()), location)
    }

}