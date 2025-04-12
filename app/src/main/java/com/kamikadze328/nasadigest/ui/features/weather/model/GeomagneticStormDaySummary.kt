package com.kamikadze328.nasadigest.ui.features.weather.model

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity(
    tableName = "geomagnetic_storms",
)
data class GeomagneticStormDaySummary(
    @ColumnInfo("max_kp_index") val maxKpIndex: Double?,
    @ColumnInfo("kp_count") val kpCount: Int,
    @PrimaryKey @ColumnInfo("date") val date: String,
)