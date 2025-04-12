package com.kamikadze328.nasadigest.ui.features.weather.model

import androidx.compose.runtime.Immutable

@Immutable
data class WeatherUiState(
    val geomagneticStormsState: GeomagneticStormsState = GeomagneticStormsState.Loading,
    val solarFlareState: SolarFlareState = SolarFlareState.Loading,
)