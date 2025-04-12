package com.kamikadze328.nasadigest.data.features.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DonkiFLRDto(
    @SerialName("beginTime")
    val beginTime: String?,
    @SerialName("peakTime")
    val peakTime: String?,
    @SerialName("classType")
    val classType: String,
)