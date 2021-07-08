package com.app.weather.presentation.util

import android.location.Location
import android.view.View

fun Location.getLatLong() = "${this.latitude},${this.longitude}"

fun String.getURL() = "https://$this"

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}