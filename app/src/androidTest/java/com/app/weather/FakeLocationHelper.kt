package com.app.weather

import androidx.fragment.app.Fragment
import com.app.weather.presentation.util.location.LocationHelper

class FakeLocationHelper : LocationHelper {
    override fun checkLocationPermission(
        fragment: Fragment,
        isGrantedAction: (latLong: String) -> Unit,
        featureUnavailableAction: () -> Unit
    ) {
        isGrantedAction("")
    }

    override fun getLatLong(fragment: Fragment, isGrantedAction: (latLong: String) -> Unit) {

    }

    override fun hasLocationPermission(fragment: Fragment): Boolean {
        return true
    }
}