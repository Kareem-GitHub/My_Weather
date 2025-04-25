package com.example.myweather.presentation.ui.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myweather.presentation.ui.home.components.LogoutIcon
import com.example.myweather.presentation.viewmodel.AuthViewModel
import com.example.myweather.presentation.viewmodel.WeatherViewModel
import com.example.myweather.presentation.viewmodel.WeatherState
import com.example.myweather.presentation.ui.home.components.WeatherCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    weatherViewModel: WeatherViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var city by remember { mutableStateOf("") } // User input for city name
    val weatherState by weatherViewModel.weatherState.collectAsState()
    val isUserLoggedOut by authViewModel.isUserLoggedOut.collectAsState()

    LaunchedEffect(isUserLoggedOut) {
        if (isUserLoggedOut) {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("My Weather")
                    }
                },
                actions = {
                    IconButton(onClick = { authViewModel.signOut() }) {
                        Icon(
                            imageVector = LogoutIcon,
                            contentDescription = "Logout"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.Transparent
    ) { paddingValues ->

        // Main content area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF2196F3), Color(0xFF9C27B0))
                    )
                )
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Search bar
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Enter city name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Search button
            Button(
                onClick = {
                    if (city.isNotBlank()) {
                        weatherViewModel.fetchWeather(city)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Weather information
            when (val state = weatherState) {
                is WeatherState.Idle -> {
                    Text("Search for a city to get the weather info.")
                }

                is WeatherState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is WeatherState.Success -> {
                    WeatherCard(state)
                }

                is WeatherState.Error -> {
                    Text(
                        "Error: ${state.message}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
