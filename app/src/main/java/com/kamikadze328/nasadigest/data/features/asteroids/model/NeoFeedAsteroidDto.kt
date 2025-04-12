package com.kamikadze328.nasadigest.data.features.asteroids.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NeoFeedAsteroidDto(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("estimated_diameter")
    val estimatedDiameter: NeoFeedAsteroidDiameterDto?,
    @SerialName("is_potentially_hazardous_asteroid")
    val isPotentiallyHazardousAsteroid: Boolean?,
    @SerialName("absolute_magnitude_h")
    val absoluteMagnitudeH: Double?,
)