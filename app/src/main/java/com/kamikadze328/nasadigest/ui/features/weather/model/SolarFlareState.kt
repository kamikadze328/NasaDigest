package com.kamikadze328.nasadigest.ui.features.weather.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface SolarFlareState {
    data object Loading : SolarFlareState
    data class Error(val message: String?) : SolarFlareState
    data class Data(val data: ImmutableList<SolarFlareDaySummary>) : SolarFlareState
}