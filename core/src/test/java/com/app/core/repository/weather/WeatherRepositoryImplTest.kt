package com.app.core.repository.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.MainCoroutineRule
import com.app.core.data.repository.home.WeatherRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun forecast() {
        val response = weatherRepository.forecast()

    }

    @Test
    fun getWeatherDataSource() {
    }
}