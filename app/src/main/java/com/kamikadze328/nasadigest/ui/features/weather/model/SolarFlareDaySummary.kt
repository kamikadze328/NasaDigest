package com.kamikadze328.nasadigest.ui.features.weather.model

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity(
    tableName = "solar_flares",
)
data class SolarFlareDaySummary(
    @ColumnInfo("max_flare_class_full") val maxFlareClassFull: String,
    @ColumnInfo("max_flare_class") val maxFlareClass: Class?,
    @ColumnInfo("flare_count") val flareCount: Int,
    @ColumnInfo("peak_time_of_max_flare") val peakTimeOfMaxFlare: String,
    @PrimaryKey @ColumnInfo("date") val date: String,
) {
    enum class Class {
        X,
        M,
        C,
        B,
        A;
    }
}

