package com.kamikadze328.nasadigest.ui.features.weather

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kamikadze328.nasadigest.ui.navigation.Destination

fun NavGraphBuilder.weatherDestination() {
    composable<Destination.Weather> { WeatherScreenUi() }
}