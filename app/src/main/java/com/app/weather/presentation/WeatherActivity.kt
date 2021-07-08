package com.app.weather.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.weather.R
import com.app.weather.presentation.util.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity(R.layout.activity_weather) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("netw_availa", NetworkUtil.isNetworkDisconnected(this).toString())
        if (NetworkUtil.isNetworkDisconnected(this)) {
            Toast.makeText(
                this, getString(R.string.network_unavailable), Toast.LENGTH_SHORT
            ).show()
        }
    }
}
