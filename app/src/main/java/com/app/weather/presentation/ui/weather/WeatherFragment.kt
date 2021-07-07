package com.app.weather.presentation.ui.weather

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.core.domain.ResultWrapper
import com.app.core.domain.weather.ForecastResponse
import com.app.core.domain.weather.Hour
import com.app.weather.R
import com.app.weather.databinding.FragmentWeatherBinding
import com.app.weather.presentation.util.location.LocationHelper
import com.app.weather.presentation.util.viewBinding
import com.app.weather.presentation.weather.getURL
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherFragment(val locationHelper: LocationHelper) : Fragment(R.layout.fragment_weather) {

    private val binding by viewBinding(FragmentWeatherBinding::bind)
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val REQUEST_CHECK_SETTINGS = 3324

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeInFragment()
        getDefaultData()
        askLocationPermission()



//        val manager =
//            getSystemService(requireContext(),LOCATION_SERVICE::class.java)
//
//        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            Toast.makeText(requireContext(), "gps is not enabled", Toast.LENGTH_SHORT).show()
//        }


//        getLocationDialog()
    }

    private fun getDefaultData() {
        if (!locationHelper.hasLocationPermission(this)) {
            weatherViewModel.forecast("New York")
        }
    }

    private fun askLocationPermission() {
        locationHelper.checkLocationPermission(this, {
            weatherViewModel.forecast(it)
        }, {
            Toast.makeText(
                requireContext(),
                getString(R.string.location_permission_denied_message),
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    private fun observeInFragment() {
        weatherViewModel.forecastLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Success -> {
                    initSectionToday(it.data)
                    initSectionForecast(it.data)
                    initRecyclerView(it.data.forecast.forecastday[0].hour)
                }
                is ResultWrapper.Error -> {
                }
                is ResultWrapper.InProgress -> {
                }
            }
        }
    }

    private fun initRecyclerView(hours: List<Hour>) {
        val adapter = HourlyForecastRVAdapter(hours)
        binding.hourlyForecastRecyclerView.adapter = adapter
    }

    private fun initSectionToday(data: ForecastResponse) {
        binding.currentTemperatureTextView.text = "${data.current.temp_c.toInt()}"
        binding.currentConditionTextView.text = data.current.condition.text
        binding.currentLocationTextView.text = data.location.name

        binding.windSpeedTextView.text = "${data.current.wind_kph} km/h"
        binding.humidityTextView.text = data.current.humidity.toString()
        binding.cloudPercentageTextView.text = data.current.cloud.toString()
        binding.pressureTextView.text = data.current.pressure_in.toString()
    }

    private fun initSectionForecast(data: ForecastResponse) {
//        binding.day1TextView.text = data.forecast.forecastday[0].date
        binding.temp1TextView.text = "${data.forecast.forecastday[0].day.avgtemp_c} C"
        binding.humidity1TextView.text = "${data.forecast.forecastday[0].day.avghumidity} C"
        Glide.with(this).load(data.forecast.forecastday[0].day.condition.icon.getURL())
            .into(binding.weathIc1ImageView)

//        binding.day2TextView.text = data.forecast.forecastday[1].date
        binding.temp2TextView.text = "${data.forecast.forecastday[1].day.avgtemp_c} C"
        binding.humidity2TextView.text = "${data.forecast.forecastday[1].day.avghumidity} C"
        Glide.with(this).load(data.forecast.forecastday[1].day.condition.icon.getURL())
            .into(binding.weathIc2ImageView)

        binding.day3TextView.text = data.forecast.forecastday[2].date
        binding.temp3TextView.text = "${data.forecast.forecastday[2].day.avgtemp_c} C"
        binding.humidity3TextView.text = "${data.forecast.forecastday[2].day.avghumidity} C"
        Glide.with(this).load(data.forecast.forecastday[2].day.condition.icon.getURL())
            .into(binding.weathIc3ImageView)

//        binding.day2TextView.text = data.forecast.forecastday[0].date

        Glide.with(this).load(data.current.condition.icon.getURL())
            .into(binding.currentConditionImageView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_CHECK_SETTINGS -> {
                askLocationPermission()
            }
        }
    }
}