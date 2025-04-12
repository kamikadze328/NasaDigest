package com.kamikadze328.nasadigest.data.features.asteroids.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NeoFeedAsteroidDiameterDto(
    @SerialName("kilometers")
    val kilometers: NeoFeedAsteroidDiameterKilometersDto?,
)