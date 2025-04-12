package com.kamikadze328.nasadigest.ui.features.weather.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface GeomagneticStormsState {
    data object Loading : GeomagneticStormsState
    data class Error(val message: String?) : GeomagneticStormsState
    data class Data(val data: ImmutableList<GeomagneticStormDaySummary>) : GeomagneticStormsState
}