package com.kamikadze328.nasadigest.data.features.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DonkiKpIndexDto(
    @SerialName("kpIndex")
    val kpIndex: Double?,
)