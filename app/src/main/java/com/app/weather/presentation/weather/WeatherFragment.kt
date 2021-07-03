package com.app.weather.presentation.weather

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.weather.R
import com.app.weather.presentation.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private val homeViewModel: HomeViewModel by viewModels()
}