package com.app.weather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.data.repository.home.HomeRepository
import com.app.core.domain.*
import com.app.core.interactor.weather.ForecastInteractor
import com.app.core.interactor.weather.HomeInteractors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

import org.junit.Rule
import retrofit2.Response

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(HomeInteractors(ForecastInteractor(FakeHomeRepository())))
    }

    @Test
    fun forecast() {
        homeViewModel.forecast()

        val current = Current(
            1, Condition(0,"",""), 1.0, 1.0, 1.0,
            1.0, 1, 1, "", 1, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1, "",1.0,1.0
        )

        val location = Location("",1.0,"",1,1.0,"",
            "","")

        val expectedResp = ForecastResponse(current, Forecast(emptyList()), location)

        assertThat(homeViewModel._forecastLiveData.value).isEqualTo(Response.success(expectedResp).body())
    }
}