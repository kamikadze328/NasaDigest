package com.kamikadze328.nasadigest.ui.features.asteroids.list.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface AsteroidsListUiState {
    @Immutable
    data class Success(val data: AsteroidListUi) : AsteroidsListUiState
    data class Error(val error: String?) : AsteroidsListUiState
    data object Loading : AsteroidsListUiState
}