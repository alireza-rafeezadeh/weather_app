package com.app.weather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.domain.*
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
        homeViewModel = HomeViewModel(FakeHomeRepository())
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

        assertThat(homeViewModel._foecastLiveData.value).isEqualTo(Response.success(expectedResp).body())
    }
}