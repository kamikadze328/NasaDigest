package com.kamikadze328.nasadigest.ui.features.weather.model

sealed interface WeatherUiEvent {
    data object OnRefreshGeomagneticStorm : WeatherUiEvent
    data object OnRefreshSolarFlare : WeatherUiEvent
}