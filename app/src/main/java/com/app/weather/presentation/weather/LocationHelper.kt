package com.app.weather.presentation.weather

import androidx.fragment.app.Fragment


interface LocationHelper {
    fun checkLocationPermission(
        fragment: Fragment,
        isGrantedAction: (latLong: String) -> Unit,
        featureUnavailableAction: () -> Unit
    )

    fun getLatLong(fragment: Fragment, isGrantedAction: (latLong: String) -> Unit)
}