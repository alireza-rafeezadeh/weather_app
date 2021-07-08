package com.app.weather.presentation.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.domain.ResultWrapper
import com.app.core.interactor.weather.ForecastInteractor
import com.app.core.interactor.weather.WeatherInteractors
import com.app.weather.presentation.ui.weather.WeatherViewModel
import com.app.weather.util.AppCoroutineRule
import com.app.weather.util.ForecastMockData
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
        weatherViewModel = WeatherViewModel(WeatherInteractors(ForecastInteractor(
            FakeWeatherRepository()
        )))
    }

    @Test
    fun forecast() {
        weatherViewModel.forecast("")
        ForecastMockData.forecastFakeResponse().also { expectedResp ->
        assertThat(weatherViewModel.forecastLiveData.value)
            .isEqualTo(ResultWrapper.Success(expectedResp))
        }
    }
}
