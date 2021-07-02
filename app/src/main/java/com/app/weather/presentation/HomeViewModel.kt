package com.app.weather.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.core.data.repository.HomeRepository
import com.app.core.domain.ForecastResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(homeRepository : HomeRepository) : ViewModel() {

    var _foecastLiveData = MutableLiveData<ForecastResponse>()
    val foecastLiveData : LiveData<ForecastResponse> = _foecastLiveData

    fun forecast() {
        Log.i("forecast_tag", "forecast: home view model called!")
    }
}