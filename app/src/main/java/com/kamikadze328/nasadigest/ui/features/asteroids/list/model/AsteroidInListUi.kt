package com.kamikadze328.nasadigest.ui.features.asteroids.list.model

import androidx.compose.runtime.Immutable

@Immutable
data class AsteroidInListUi(
    val id: String,
    val name: String,
    val diameterMin: Double?,
    val diameterMax: Double?,
    val isPotentiallyHazardousAsteroid: Boolean,
    val absoluteMagnitudeH: Double?,
)