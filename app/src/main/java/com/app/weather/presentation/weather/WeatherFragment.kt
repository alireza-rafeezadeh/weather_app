package com.app.weather.presentation.weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.core.data.repository.ResultWrapper
import com.app.core.domain.ForecastResponse
import com.app.weather.R
import com.app.weather.databinding.FragmentWeatherBinding
import com.app.weather.presentation.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val binding by viewBinding(FragmentWeatherBinding::bind)
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel.forecast()

        observeInFragment()
    }

    private fun observeInFragment() {
        weatherViewModel.forecastLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is ResultWrapper.Success -> {
                    initSectionToday(it.data)
                    initSectionForecast(it.data)
                }
                is ResultWrapper.Error -> {

                }
                is ResultWrapper.InProgress -> {

                }
            }
        }
    }


    private fun initSectionToday(data: ForecastResponse) {
        binding.currentTemperatureTextView.text = "${data.current.temp_c.toInt()}"
        binding.currentConditionTextView.text = data.current.condition.text
        binding.currentLocationTextView.text = data.location.name

        binding.windSpeedTextView.text = "${data.current.wind_kph} km/h"
        binding.humidityTextView.text = data.current.humidity.toString()
        binding.cloudPercentageTextView.text = data.current.cloud.toString()
        binding.pressureTextView.text = data.current.pressure_in.toString()
//        binding.sunRiseTextView.text = data.current.sun.toString()
    }

    private fun initSectionForecast(data: ForecastResponse) {
        binding.day1TextView.text = data.forecast.forecastday[1].date
        binding.temp1TextView.text = "${data.forecast.forecastday[1].day.avgtemp_c} C"
        binding.humidity1TextView.text = "${data.forecast.forecastday[1].day.avghumidity} C"

        binding.day2TextView.text = data.forecast.forecastday[2].date
        binding.temp2TextView.text = "${data.forecast.forecastday[2].day.avgtemp_c} C"
        binding.humidity2TextView.text = "${data.forecast.forecastday[2].day.avghumidity} C"

//        binding.day3TextView.text = data.forecast.forecastday[3].date
//        binding.temp3TextView.text = "${data.forecast.forecastday[3].day.avgtemp_c} C"
//        binding.humidity3TextView.text = "${data.forecast.forecastday[3].day.avghumidity} C"

//        binding.day2TextView.text = data.forecast.forecastday[0].date
    }

}