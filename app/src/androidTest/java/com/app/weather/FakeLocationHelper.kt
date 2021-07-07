package com.app.weather

import androidx.fragment.app.Fragment
import com.app.weather.presentation.weather.LocationHelper

class FakeLocationHelper constructor(): LocationHelper {
    override fun checkLocationPermission(
        fragment: Fragment,
        isGrantedAction: (latLong: String) -> Unit,
        featureUnavailableAction: () -> Unit
    ) {
        isGrantedAction("")
    }

    override fun getLatLong(fragment: Fragment, isGrantedAction: (latLong: String) -> Unit) {

    }
}