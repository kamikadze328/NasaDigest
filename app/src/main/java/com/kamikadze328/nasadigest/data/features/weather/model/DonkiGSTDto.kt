package com.kamikadze328.nasadigest.data.features.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DonkiGSTDto(
    @SerialName("startTime")
    val startTime: String?,
    @SerialName("allKpIndex")
    val allKpIndex: List<DonkiKpIndexDto>?,
)