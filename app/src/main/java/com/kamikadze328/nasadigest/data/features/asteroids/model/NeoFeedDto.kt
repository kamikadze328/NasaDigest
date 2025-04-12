package com.kamikadze328.nasadigest.data.features.asteroids.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NeoFeedDto(
    @SerialName("near_earth_objects")
    val nearEarthObjects: Map<String, List<NeoFeedAsteroidDto>>,
)