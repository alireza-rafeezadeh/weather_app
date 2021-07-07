package com.app.weather.presentation.weather

import android.location.Location

fun Location.getLatLong() = "${this.latitude},${this.longitude}"

fun String.getURL() = "https://$this"
