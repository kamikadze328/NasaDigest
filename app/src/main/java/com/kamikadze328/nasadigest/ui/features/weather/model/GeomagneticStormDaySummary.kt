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
    @ColumnInfo("max_kp_index") val maxKpIndex: Int?,
    @ColumnInfo("kp_count") val kpCount: Int,
    @PrimaryKey @ColumnInfo("date") val date: String,
) {
    val maxKpIndexType: KpType
        get() = KpType.entries.firstOrNull { it.range.contains(maxKpIndex ?: 0) }
            ?: KpType.Quiet

    enum class KpType(val range: IntRange) {
        Quiet(0..4),
        MinorStorm(5..5),
        ModerateStorm(6..6),
        StrongStorm(7..8),
        ExtremeStorm(9..9),
    }
}