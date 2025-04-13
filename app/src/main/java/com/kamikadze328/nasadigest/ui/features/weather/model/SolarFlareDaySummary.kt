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
    @ColumnInfo("max_flare_class") val maxFlareClass: String,
    @ColumnInfo("flare_count") val flareCount: Int,
    @ColumnInfo("peak_time_of_max_flare") val peakTimeOfMaxFlare: String,
    @PrimaryKey @ColumnInfo("date") val date: String,
) {
    val maxFlareClassName: Class
        get() {
            return when {
                maxFlareClass.startsWith("X", ignoreCase = true) -> Class.X
                maxFlareClass.startsWith("M", ignoreCase = true) -> Class.M
                maxFlareClass.startsWith("C", ignoreCase = true) -> Class.C
                maxFlareClass.startsWith("B", ignoreCase = true) -> Class.B
                maxFlareClass.startsWith("A", ignoreCase = true) -> Class.A
                else -> Class.A
            }
        }

    enum class Class {
        X,
        M,
        C,
        B,
        A;
    }
}

