package com.app.core.interactor.weather

import javax.inject.Inject

data class WeatherInteractors @Inject constructor(val forecastInteractor: ForecastInteractor)