package com.app.weather.presentation.weather

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.weather.R
import com.app.weather.presentation.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private val weatherViewModel: WeatherViewModel by viewModels()
}