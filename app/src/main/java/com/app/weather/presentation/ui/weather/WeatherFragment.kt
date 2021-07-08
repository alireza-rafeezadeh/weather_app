package com.app.weather.presentation.ui.weather

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.core.domain.RequestCodes
import com.app.core.domain.ResultWrapper
import com.app.core.domain.weather.ForecastResponse
import com.app.core.domain.weather.Hour
import com.app.weather.R
import com.app.weather.databinding.FragmentWeatherBinding
import com.app.weather.presentation.util.*
import com.app.weather.presentation.util.location.LocationHelper
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment(val locationHelper: LocationHelper) : Fragment(R.layout.fragment_weather) {

    private val binding by viewBinding(FragmentWeatherBinding::bind)
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationHelper.registerPermissionLauncher(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeInFragment()
        setOnClickListeners()
        getWeatherInfo()
    }

    private fun setOnClickListeners() {
        binding.weatherSwipeRefreshLayout.setOnRefreshListener {
            getWeatherInfo(true)
        }
    }

    private fun getWeatherInfo(forceUpdate: Boolean = false) {
        if (weatherViewModel.forecastLiveData.value != null && !forceUpdate)
            return

        locationHelper.askForLocationPermission(this, { latLong ->
            weatherViewModel.forecast(latLong)
        }, { error ->
            Toast.makeText(
                requireContext(),
                error,
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    private fun observeInFragment() {
        weatherViewModel.forecastLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Success -> {
                    binding.weatherSwipeRefreshLayout.stopRefreshing()
                    initSectionToday(it.data)
                    initSectionForecast(it.data)
                    initRecyclerView(it.data.forecast.forecastday[0].hour)
                }
                is ResultWrapper.ErrorString -> {
                    binding.weatherSwipeRefreshLayout.stopRefreshing()
                    Toast.makeText(requireContext(), it.exception, Toast.LENGTH_SHORT).show()
                    binding.weatherProgressBar.gone()
                }
                is ResultWrapper.InProgress -> {
                    binding.weatherSwipeRefreshLayout.startRefreshing()
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
        binding.humidityTextView.text = "${data.current.humidity}%"
        binding.cloudPercentageTextView.text = "${data.current.cloud}%"
        binding.pressureTextView.text = data.current.pressure_in.toString()
    }

    private fun initSectionForecast(data: ForecastResponse) {
        binding.temp1TextView.text = "${data.forecast.forecastday[0].day.avgtemp_c}º"
        binding.humidity1TextView.text = "${data.forecast.forecastday[0].day.avghumidity}%"
        Glide.with(this).load(data.forecast.forecastday[0].day.condition.icon.getURL())
            .into(binding.weathIc1ImageView)

        binding.temp2TextView.text = "${data.forecast.forecastday[1].day.avgtemp_c}º"
        binding.humidity2TextView.text = "${data.forecast.forecastday[1].day.avghumidity}%"
        Glide.with(this).load(data.forecast.forecastday[1].day.condition.icon.getURL())
            .into(binding.weathIc2ImageView)

        binding.day3TextView.text = data.forecast.forecastday[2].date
        binding.temp3TextView.text = "${data.forecast.forecastday[2].day.avgtemp_c}º"
        binding.humidity3TextView.text = "${data.forecast.forecastday[2].day.avghumidity}%"
        Glide.with(this).load(data.forecast.forecastday[2].day.condition.icon.getURL())
            .into(binding.weathIc3ImageView)

        Glide.with(this).load(data.current.condition.icon.getURL())
            .into(binding.currentConditionImageView)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RequestCodes.LOCATION -> when (resultCode) {
                Activity.RESULT_OK -> {
                    locationHelper.startLocationUpdates(this)
                }
                Activity.RESULT_CANCELED -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.gps_denied_error),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }
}