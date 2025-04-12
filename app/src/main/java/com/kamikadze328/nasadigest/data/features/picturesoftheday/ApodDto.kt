package com.kamikadze328.nasadigest.data.features.picturesoftheday

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApodDto(
    @SerialName("copyright")
    val copyright: String?,
    @SerialName("explanation")
    val explanation: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("date")
    val date: String?,
)