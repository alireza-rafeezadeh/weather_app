package com.app.core.interactor.weather

import javax.inject.Inject


data class HomeInteractors @Inject constructor(val forecastInteractor: ForecastInteractor)

