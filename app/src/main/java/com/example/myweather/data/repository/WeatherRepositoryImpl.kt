package com.example.myweather.data.repository

import com.example.myweather.data.api.WeatherApiService
import com.example.myweather.data.model.WeatherResponse
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService
) : WeatherRepository {

    private val dotenv = Dotenv.load()
    private val apiKey = dotenv["API_KEY"]

    override suspend fun getCurrentWeather(city: String): WeatherResponse {
        return apiService.getCurrentWeather(city = city, apiKey = apiKey)
    }
}
