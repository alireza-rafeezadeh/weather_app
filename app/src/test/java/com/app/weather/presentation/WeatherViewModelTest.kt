package com.app.weather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.domain.*
import com.app.core.interactor.weather.ForecastInteractor
import com.app.core.interactor.weather.WeatherInteractors
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = AppCoroutineRule()

    lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun setUp() {
        weatherViewModel = WeatherViewModel(WeatherInteractors(ForecastInteractor(FakeWeatherRepository())))
    }

    @Test
    fun forecast() {
        weatherViewModel.forecast()

        val current = Current(
            1, Condition(0,"",""), 1.0, 1.0, 1.0,
            1.0, 1, 1, "", 1, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1, "",1.0,1.0
        )

        val location = Location("",1.0,"",1,1.0,"",
            "","")

        val expectedResp = ForecastResponse(current, Forecast(emptyList()), location)

        assertThat(weatherViewModel._forecastLiveData.value).isEqualTo(Response.success(expectedResp).body())
    }
}