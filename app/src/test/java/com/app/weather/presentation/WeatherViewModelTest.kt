package com.app.weather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.data.repository.ResultWrapper
import com.app.core.domain.Condition
import com.app.core.domain.Current
import com.app.core.domain.Forecast
import com.app.core.domain.ForecastResponse
import com.app.core.domain.Location
import com.app.core.interactor.weather.ForecastInteractor
import com.app.core.interactor.weather.WeatherInteractors
import com.app.weather.presentation.weather.WeatherViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
        weatherViewModel.forecast("")

        val current = Current(
            1, Condition(0, "", ""), 1.0, 1.0, 1.0,
            1.0, 1, 1, "", 1, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1, "", 1.0, 1.0
        )

        val location = Location("", 1.0, "", 1, 1.0, "",
            "", "")

        val expectedResp = ForecastResponse(current, Forecast(emptyList()), location)

//        ResultWrapper.Success(expectedResp)
        assertThat(weatherViewModel.forecastLiveData.value)
            .isEqualTo(ResultWrapper.Success(expectedResp))
    }
}
