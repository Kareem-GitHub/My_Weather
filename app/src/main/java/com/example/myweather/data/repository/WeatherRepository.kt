package com.example.myweather.data.repository

import com.example.myweather.data.model.WeatherResponse

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): WeatherResponse
}