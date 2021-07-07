package com.app.weather.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.domain.ForecastResponse
import com.app.core.domain.ResultWrapper
import com.app.core.interactor.weather.WeatherInteractors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(val interactor: WeatherInteractors) : ViewModel() {

    private var _forecastLiveData = MutableLiveData<ResultWrapper<ForecastResponse>>()
    val forecastLiveData: LiveData<ResultWrapper<ForecastResponse>> = _forecastLiveData

    fun forecast(latLong: String) = viewModelScope.launch {
        val resp = interactor.forecastInteractor.forecast(latLong)
        resp.collect {
            _forecastLiveData.postValue(it)
        }
    }
}
