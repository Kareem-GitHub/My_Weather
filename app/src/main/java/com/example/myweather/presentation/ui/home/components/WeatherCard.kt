package com.example.myweather.presentation.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myweather.R
import com.example.myweather.presentation.viewmodel.WeatherState

@Composable
fun WeatherCard(state: WeatherState.Success) {
    val weather = state.data
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // City Name
            WeatherInfoRow(
                iconResId = R.drawable.ic_location,
                label = "City",
                value = weather.name,
                contentDescription = "Location Icon"
            )

            // Temperature
            WeatherInfoRow(
                iconResId = R.drawable.ic_temperature,
                label = "Temperature",
                value = "${weather.main.temp}°C",
                contentDescription = "Temperature Icon"
            )

            // Feels Like
            WeatherInfoRow(
                iconResId = R.drawable.ic_feels_like,
                label = "Feels Like",
                value = "${weather.main.feels_like}°C",
                contentDescription = "Feels Like Icon"
            )

            // Weather Condition
            WeatherInfoRow(
                iconResId = R.drawable.ic_weather_condition,
                label = "Weather",
                value = "${weather.weather.firstOrNull()?.main} - ${weather.weather.firstOrNull()?.description}",
                contentDescription = "Weather Icon"
            )

            // Humidity
            WeatherInfoRow(
                iconResId = R.drawable.ic_humidity,
                label = "Humidity",
                value = "${weather.main.humidity}%",
                contentDescription = "Humidity Icon"
            )

            // Wind Speed
            WeatherInfoRow(
                iconResId = R.drawable.ic_wind_speed,
                label = "Wind Speed",
                value = "${weather.wind.speed} m/s",
                contentDescription = "Wind Speed Icon"
            )
        }
    }
}

