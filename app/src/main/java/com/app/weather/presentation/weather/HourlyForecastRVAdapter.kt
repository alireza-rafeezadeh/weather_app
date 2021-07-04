package com.app.weather.presentation.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.core.domain.Hour
import com.app.weather.databinding.ItemHourlyForecastBinding


class HourlyForecastRVAdapter(val hours: List<Hour>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemHourlyForecastBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bindView(hours[position])
    }

    override fun getItemCount(): Int {
        return hours.size
    }

    inner class ItemViewHolder(private val binding: ItemHourlyForecastBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bindView(hour: Hour) {
            with(binding) {
                hourTextView.text = hour.time.substringAfter(" ")
                temperatureTextView.text = hour.temp_c.toString()
                humidityTextView.text = hour.humidity.toString()
            }
        }
    }
}