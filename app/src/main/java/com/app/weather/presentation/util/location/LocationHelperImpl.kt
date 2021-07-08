package com.app.weather.presentation.util.location

import android.Manifest
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.app.core.domain.RequestCodes
import com.app.weather.R
import com.app.weather.presentation.util.getLatLong
import com.google.android.gms.common.api.*
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class LocationHelperImpl @Inject constructor() : LocationHelper {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var onLatLongReceived: (latLong: String) -> Unit = { }
    private var onError: (error: String) -> Unit = { }
    private var googleApiClient: GoogleApiClient? = null

    private lateinit var requestPermissionLauncher : ActivityResultLauncher<String>

    override fun registerPermissionLauncher(
        fragment: Fragment
    ) {
       requestPermissionLauncher =
            fragment.registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    enableGPS(fragment)
                } else {
                    onError("Location permission was not granted.")
                }
            }
    }

    override fun askForLocationPermission(
        fragment: Fragment,
        isGrantedAction: (latLong: String) -> Unit,
        featureUnavailableAction: (error: String) -> Unit
    ) {
        onLatLongReceived = isGrantedAction
        /*
        onError = featureUnavailableAction
        val requestPermissionLauncher =
            fragment.registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
//                    getLatLong(fragment, isGrantedAction)
                    enableGPS(fragment)
                } else {
                    onError("Location permission was not granted.")
                }
            }*/

        when {
            ContextCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
//                getLatLong(fragment, isGrantedAction)
                enableGPS(fragment)
            }
            /*shouldShowRequestPermissionRationale(...) -> {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            showInContextUI(...)
        }*/
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        }
    }


    private fun enableGPS(context: Fragment) {
        googleApiClient = GoogleApiClient.Builder(context.requireContext())
            .addApi(LocationServices.API)
            .addConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
                override fun onConnected(bundle: Bundle?) {}
                override fun onConnectionSuspended(i: Int) {
                    googleApiClient?.connect()
                }
            })
            .addOnConnectionFailedListener {
            }.build()
        googleApiClient?.connect()

        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 30 * 1000.toLong()
        locationRequest.fastestInterval = 5 * 1000.toLong()
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)
        val result: PendingResult<LocationSettingsResult> =
            LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())
        result.setResultCallback { result ->
            val status: Status = result.status
            when (status.statusCode) {
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
//                    status.startResolutionForResult(
//                        requireActivity(),
//                        REQUESTLOCATION
//                    )

                    context.startIntentSenderForResult(
                        status.getResolution().getIntentSender(),
                        RequestCodes.LOCATION,
                        null,
                        0,
                        0,
                        0,
                        null
                    );
//                    startIntentSenderForResult()
                } catch (e: IntentSender.SendIntentException) {
                }
                else -> {
//                    Toast.makeText(context.requireContext() , "permission granted", Toast.LENGTH_SHORT).show()
                    startLocationUpdates(context)
                }
            }
        }
    }

    override fun startLocationUpdates(fragment: Fragment) {

        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(fragment.requireActivity())

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    Log.i("location_updated", "onLocationResult: ")
                    // Update UI with location data
                    // ...
                    getLatLong(fragment)
                    stopLocationUpdates()
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun getLatLong(fragment: Fragment) {
        if (ActivityCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                Log.i("location_tag", "performAction: -> $location ")

                Log.i("lat_long_tag", "${location?.latitude}/${location?.longitude}")

                location?.getLatLong()?.let {
//                    isGrantedAction(it)
                    onLatLongReceived(it)
                }
                if (location == null) {
//                    getLocationDialog(fragment )
//                    startLocationUpdates(fragment, locationRequest)
                }
            }
    }


    override fun hasLocationPermission(fragment: Fragment): Boolean {
        if (ActivityCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false
        }
        return true
    }


    protected val REQUEST_CHECK_SETTINGS = 0x1

    override fun getLocationDialog(context: Fragment) {
        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = (30 * 1000).toLong()
        locationRequest.fastestInterval = (5 * 1000).toLong()
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true) //this is the key ingredient
        val result: Task<LocationSettingsResponse> =
            LocationServices.getSettingsClient(context.requireActivity())
                .checkLocationSettings(builder.build())
        result.addOnCompleteListener(OnCompleteListener<LocationSettingsResponse?> { task ->
            try {
                val response = task.getResult(ApiException::class.java)

                //TODO:
                startLocationUpdates(context)
                // All location settings are satisfied. The client can initialize location
                // requests here.
            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                        // Location settings are not satisfied. But could be fixed by showing the
                        // user a dialog.
                        try {
                            // Cast to a resolvable exception.
                            val resolvable = exception as ResolvableApiException
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            resolvable.startResolutionForResult(
                                context.requireActivity(),
                                REQUEST_CHECK_SETTINGS //TODO: change this!
                            )

                            //TODO:
//                            startLocationUpdates(context )
                        } catch (e: IntentSender.SendIntentException) {
                            onError(e.message ?: context.getString(R.string.unknown_error))
                            // Ignore the error.
                        } catch (e: ClassCastException) {
                            // Ignore, should be an impossible error.
                            onError(e.message ?: context.getString(R.string.unknown_error))
                        } catch (e: Exception) {
                            onError(e.message ?: context.getString(R.string.unknown_error))
                        }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        onError(context.getString(R.string.service_unavailable))
                    }
                }
            }
        })
    }


}