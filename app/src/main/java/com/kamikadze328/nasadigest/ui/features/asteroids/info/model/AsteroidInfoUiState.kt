package com.kamikadze328.nasadigest.ui.features.asteroids.info.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface AsteroidInfoUiState {
    data object Loading : AsteroidInfoUiState
    data class Error(val message: String?) : AsteroidInfoUiState
    data class Success(val asteroid: AsteroidInfoUi) : AsteroidInfoUiState
}