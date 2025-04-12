package com.kamikadze328.nasadigest.data.features.asteroids.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NeoLookupOrbitalDataDto(
    @SerialName("first_observation_date")
    val firstObservationDate: String?,
    @SerialName("orbit_class")
    val orbitClass: NeoLookupOrbitClassDto?,
)