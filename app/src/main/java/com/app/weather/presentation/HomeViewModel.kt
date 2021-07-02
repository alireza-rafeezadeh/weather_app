package com.app.weather.presentation

import androidx.lifecycle.ViewModel
import com.app.core.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(homeRepository : HomeRepository) : ViewModel() {
    fun forecast() {

    }
}