package com.app.weather.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.data.repository.ResultWrapper
import com.app.core.domain.ForecastResponse
import com.app.core.interactor.weather.WeatherInteractors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(val interactor: WeatherInteractors) : ViewModel() {

    var _forecastLiveData = MutableLiveData<ResultWrapper<ForecastResponse>>()
    val forecastLiveData : LiveData<ResultWrapper<ForecastResponse>> = _forecastLiveData

    init {
//        forecast()
    }

    fun forecast() = viewModelScope.launch {
        val resp = interactor.forecastInteractor.forecast()
        resp.collect {
            _forecastLiveData.postValue(it)

            if (it is ResultWrapper.Success) {
                it.data
            }

            Log.i("forcast_res_tag", "forecast: resp" + it)
        }
    }
}