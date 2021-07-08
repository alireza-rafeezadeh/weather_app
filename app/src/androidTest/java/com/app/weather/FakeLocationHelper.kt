package com.app.weather

import androidx.fragment.app.Fragment
import com.app.weather.presentation.util.location.LocationHelper

class FakeLocationHelper : LocationHelper {
    override fun registerPermissionLauncher(fragment: Fragment) {

    }
    override fun askForLocationPermission(
        fragment: Fragment,
        isGrantedAction: (latLong: String) -> Unit,
        featureUnavailableAction: (error: String) -> Unit
    ) {
        featureUnavailableAction("some random error")
    }

    override fun getLatLong(fragment: Fragment) {

    }

    override fun hasLocationPermission(fragment: Fragment): Boolean {
        return true
    }

    override fun getLocationDialog(context: Fragment) {

    }

    override fun startLocationUpdates(fragment: Fragment) {

    }

}
