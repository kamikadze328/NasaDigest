package com.kamikadze328.nasadigest.data.features.asteroids.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NeoLookupOrbitClassDto(
    @SerialName("orbit_class_description")
    val description: String?,
    @SerialName("orbit_class_type")
    val classType: String?,
)