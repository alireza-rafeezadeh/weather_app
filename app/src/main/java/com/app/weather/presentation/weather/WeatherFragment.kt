package com.app.weather.presentation.weather

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.core.data.repository.ResultWrapper
import com.app.core.domain.ForecastResponse
import com.app.core.domain.Hour
import com.app.weather.R
import com.app.weather.databinding.FragmentWeatherBinding
import com.app.weather.presentation.util.viewBinding
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val binding by viewBinding(FragmentWeatherBinding::bind)
    private val weatherViewModel: WeatherViewModel by viewModels()


    private lateinit var fusedLocationClient: FusedLocationProviderClient



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        weatherViewModel.forecast(location.getLatLong())

        observeInFragment()

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    getLatLong()
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }


        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                getLatLong()
            }
            /*shouldShowRequestPermissionRationale(...) -> {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            showInContextUI(...)
        }*/
            else -> {
                // You can directly ask for the permission.
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun getLatLong() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                // Got last known location. In some rare situations this can be null.
                Log.i("location_tag", "performAction: -> $location ")


                Log.i("lat_long_tag", "${location?.latitude}/${location?.longitude}")

                location?.getLatLong()?.let {
                    weatherViewModel.forecast(it)
                }

            }

    }

    private fun observeInFragment() {
        weatherViewModel.forecastLiveData.observe(viewLifecycleOwner) {
            when(it) {
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
//        binding.sunRiseTextView.text = data.current.sun.toString()
    }

    private fun initSectionForecast(data: ForecastResponse) {
        binding.day1TextView.text = data.forecast.forecastday[1].date
        binding.temp1TextView.text = "${data.forecast.forecastday[1].day.avgtemp_c} C"
        binding.humidity1TextView.text = "${data.forecast.forecastday[1].day.avghumidity} C"
        Glide.with(this).load(data.forecast.forecastday[1].day.condition.icon.getURL())
            .into(binding.weathIc1ImageView)

        binding.day2TextView.text = data.forecast.forecastday[2].date
        binding.temp2TextView.text = "${data.forecast.forecastday[2].day.avgtemp_c} C"
        binding.humidity2TextView.text = "${data.forecast.forecastday[2].day.avghumidity} C"
        Glide.with(this).load(data.forecast.forecastday[2].day.condition.icon.getURL())
            .into(binding.weathIc2ImageView)

//        binding.day3TextView.text = data.forecast.forecastday[3].date
//        binding.temp3TextView.text = "${data.forecast.forecastday[3].day.avgtemp_c} C"
//        binding.humidity3TextView.text = "${data.forecast.forecastday[3].day.avghumidity} C"

//        binding.day2TextView.text = data.forecast.forecastday[0].date


        Glide.with(this).load(data.current.condition.icon.getURL())
            .into(binding.currentConditionImageView)
    }

}


fun String.getURL() = "https://$this"