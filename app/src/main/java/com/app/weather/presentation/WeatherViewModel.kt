package com.app.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.core.domain.ForecastResponse
import com.app.core.interactor.weather.WeatherInteractors
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(val interactor: WeatherInteractors) : ViewModel() {

    var _forecastLiveData = MutableLiveData<ForecastResponse>()
    val forecastLiveData : LiveData<ForecastResponse> = _forecastLiveData


    fun forecast() {

    }
}