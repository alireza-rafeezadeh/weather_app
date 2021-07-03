package com.app.core.interactor.weather

import javax.inject.Inject


class HomeInteractors @Inject constructor(val forecastInteractor: ForecastInteractor)