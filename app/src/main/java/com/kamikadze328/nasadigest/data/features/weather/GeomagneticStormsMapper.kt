package com.kamikadze328.nasadigest.data.features.weather

import com.kamikadze328.nasadigest.data.features.weather.model.DonkiGSTDto
import com.kamikadze328.nasadigest.ui.common.DateParser
import com.kamikadze328.nasadigest.ui.features.weather.model.GeomagneticStormDaySummary
import javax.inject.Inject
import kotlin.math.roundToInt

class GeomagneticStormsMapper @Inject constructor(
    private val dateParser: DateParser,
) {
    fun fromDto(dto: List<DonkiGSTDto>): List<GeomagneticStormDaySummary> {
        return dto
            .mapNotNull {
                GeomagneticStormDaySummary(
                    date = dateParser.parseToPrettyPrintShort(it.startTime)
                        ?: return@mapNotNull null,
                    maxKpIndex = it.allKpIndex
                        ?.maxByOrNull { storm -> storm.kpIndex ?: 0.0 }?.kpIndex
                        ?.roundToInt(),
                    kpCount = it.allKpIndex?.size ?: 0,
                )
            }
            .reversed()
    }
}