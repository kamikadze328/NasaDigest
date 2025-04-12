package com.kamikadze328.nasadigest.ui.features.asteroids.info.model

import androidx.compose.runtime.Immutable

@Immutable
data class AsteroidInfoUi(
    val id: String,
    val name: String,
    val url: String?,
    val diameterMin: Double?,
    val diameterMax: Double?,
    val isPotentiallyHazardousAsteroid: Boolean,
    val firstObservationDate: String?,
    val absoluteMagnitudeH: Double?,
    val orbitalClassDescription: String?,
    val orbitalClassType: String?,
)