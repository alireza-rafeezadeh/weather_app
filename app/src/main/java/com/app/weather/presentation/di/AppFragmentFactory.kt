package com.app.weather.presentation.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.app.weather.presentation.util.location.LocationHelper
import com.app.weather.presentation.ui.weather.WeatherFragment
import javax.inject.Inject

class AppFragmentFactory @Inject constructor(private val locationHelper: LocationHelper) :
    FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            WeatherFragment::class.java.name -> {
                WeatherFragment(locationHelper)
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}