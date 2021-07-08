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
import com.app.weather.presentation.util.getURL
import com.app.weather.presentation.util.gone
import com.app.weather.presentation.util.location.LocationHelper
import com.app.weather.presentation.util.viewBinding
import com.app.weather.presentation.util.visible
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherFragment(val locationHelper: LocationHelper) : Fragment(R.layout.fragment_weather) {

    private val binding by viewBinding(FragmentWeatherBinding::bind)
    private val weatherViewModel: WeatherViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeInFragment()
//        askLocationPermission()

        getDefaultData()
        locationHelper.askForLocationPermission(this, {
            weatherViewModel.forecast(it)
        }, {

        })

    }

    private fun getDefaultData() {
        // TODO:
        if (!locationHelper.hasLocationPermission(this)) {
            weatherViewModel.forecast("Chicago")
        }
    }

    private fun askLocationPermission() {
        locationHelper.askForLocationPermission(this, {
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
                    binding.weatherProgressBar.gone()
                    initSectionToday(it.data)
                    initSectionForecast(it.data)
                    initRecyclerView(it.data.forecast.forecastday[0].hour)
                }
                is ResultWrapper.ErrorString -> {
                    Toast.makeText(requireContext(), it.exception, Toast.LENGTH_SHORT).show()
                    binding.weatherProgressBar.gone()
                }
                is ResultWrapper.InProgress -> {
                    binding.weatherProgressBar.visible()
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




   /* private var googleApiClient: GoogleApiClient? = null

    private fun enableLoc() {
        googleApiClient = GoogleApiClient.Builder(requireContext())
            .addApi(LocationServices.API)
            .addConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
                override fun onConnected(bundle: Bundle?) {}
                override fun onConnectionSuspended(i: Int) {
                    googleApiClient?.connect()
                }
            })
            .addOnConnectionFailedListener {
            }.build()
        googleApiClient?.connect()
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 30 * 1000.toLong()
        locationRequest.fastestInterval = 5 * 1000.toLong()
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)
        val result: PendingResult<LocationSettingsResult> =
            LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())
        result.setResultCallback { result ->
            val status: Status = result.status
            when (status.statusCode) {
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
//                    status.startResolutionForResult(
//                        requireActivity(),
//                        REQUESTLOCATION
//                    )

                    startIntentSenderForResult(status.getResolution().getIntentSender(), REQUESTLOCATION, null, 0, 0, 0, null);
//                    startIntentSenderForResult()
                } catch (e: IntentSender.SendIntentException) {
                }
                else -> {
                    Toast.makeText(requireContext(), "permission granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }*/


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
                        "GPS access is needed for getting your weather data",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

}