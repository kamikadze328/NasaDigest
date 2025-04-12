package com.kamikadze328.nasadigest.ui.features.asteroids.info.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class AsteroidInfoArgs(
    val asteroidId: String,
)
