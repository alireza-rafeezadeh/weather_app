package com.app.weather.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.core.data.repository.home.HomeRepository
import com.app.core.domain.ForecastResponse
import com.app.core.interactor.weather.ForecastInteractor
import com.app.core.interactor.weather.HomeInteractors
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(val interactor: HomeInteractors) : ViewModel() {

    var _forecastLiveData = MutableLiveData<ForecastResponse>()
    val forecastLiveData : LiveData<ForecastResponse> = _forecastLiveData


    fun forecast() {

    }
}