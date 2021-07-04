package com.app.core.repository.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.MainCoroutineRule
import com.app.core.data.repository.home.WeatherRepositoryImpl
import com.app.core.domain.*

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import retrofit2.Response


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
    fun forecast() {
        val response = weatherRepository.forecast()
        val expectedResp = forecastFakeResponse()
        assertThat(response).isEqualTo(Response.success(expectedResp).body())
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