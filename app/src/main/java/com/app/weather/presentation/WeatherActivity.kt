package com.app.weather.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.weather.R
import com.app.weather.presentation.util.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity(R.layout.activity_weather) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNetworkStatus()
    }

    private fun checkNetworkStatus() {
        if (NetworkUtil.isNetworkDisconnected(this)) {
            Toast.makeText(
                this, getString(R.string.network_unavailable), Toast.LENGTH_SHORT
            ).show()
        }
    }
}
