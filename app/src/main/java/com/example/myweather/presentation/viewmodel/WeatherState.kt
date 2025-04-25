package com.example.myweather.presentation.viewmodel

import com.example.myweather.data.model.WeatherResponse

sealed class WeatherState {
    data object Idle : WeatherState()
    data object Loading : WeatherState()
    data class Success(val data: WeatherResponse) : WeatherState()
    data class Error(val message: String) : WeatherState()
}
