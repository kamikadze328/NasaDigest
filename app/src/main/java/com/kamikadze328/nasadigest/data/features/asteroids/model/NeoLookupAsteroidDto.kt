package com.kamikadze328.nasadigest.data.features.asteroids.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NeoLookupAsteroidDto(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("nasa_jpl_url")
    val url: String?,
    @SerialName("estimated_diameter")
    val diameter: NeoFeedAsteroidDiameterDto?,
    @SerialName("is_potentially_hazardous_asteroid")
    val isPotentiallyHazardousAsteroid: Boolean?,
    @SerialName("absolute_magnitude_h")
    val absoluteMagnitudeH: Double?,
    @SerialName("orbital_data")
    val orbitalData: NeoLookupOrbitalDataDto?,
)