package com.app.weather.presentation.util.location

import androidx.fragment.app.Fragment


interface LocationHelper {
    fun checkLocationPermission(
        fragment: Fragment,
        isGrantedAction: (latLong: String) -> Unit,
        featureUnavailableAction: () -> Unit
    )

    fun getLatLong(fragment: Fragment, isGrantedAction: (latLong: String) -> Unit)

    fun hasLocationPermission(fragment: Fragment) : Boolean

    fun getLocationDialog(context : Fragment)

    fun startLocationUpdates(fragment: Fragment)
}