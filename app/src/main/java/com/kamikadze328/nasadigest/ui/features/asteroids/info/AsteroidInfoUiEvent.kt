package com.kamikadze328.nasadigest.ui.features.asteroids.info

sealed interface AsteroidInfoUiEvent {
    data object OnRefresh : AsteroidInfoUiEvent
}