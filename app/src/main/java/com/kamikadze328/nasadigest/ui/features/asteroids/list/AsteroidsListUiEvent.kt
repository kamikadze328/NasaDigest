package com.kamikadze328.nasadigest.ui.features.asteroids.list

sealed interface AsteroidsListUiEvent {
    data object OnRefresh : AsteroidsListUiEvent
}