package com.app.weather.presentation.util.location

import androidx.fragment.app.Fragment


interface LocationHelper {
    fun askForLocationPermission(
        fragment: Fragment,
        isGrantedAction: (latLong: String) -> Unit,
        featureUnavailableAction: (error : String) -> Unit
    )

    fun getLatLong(fragment: Fragment)

    fun hasLocationPermission(fragment: Fragment) : Boolean

    fun getLocationDialog(context : Fragment)

    fun startLocationUpdates(fragment: Fragment)
}