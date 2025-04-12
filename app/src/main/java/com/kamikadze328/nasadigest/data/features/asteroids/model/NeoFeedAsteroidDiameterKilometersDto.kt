package com.kamikadze328.nasadigest.data.features.asteroids.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NeoFeedAsteroidDiameterKilometersDto(
    @SerialName("estimated_diameter_max")
    val estimatedDiameterMax: Double?,
    @SerialName("estimated_diameter_min")
    val estimatedDiameterMin: Double?,
)