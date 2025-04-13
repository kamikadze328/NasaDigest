package com.kamikadze328.nasadigest.data.features.weather

import com.kamikadze328.nasadigest.data.features.weather.model.DonkiFLRDto
import com.kamikadze328.nasadigest.ui.common.DateParser
import com.kamikadze328.nasadigest.ui.common.prettyPrintShort
import com.kamikadze328.nasadigest.ui.features.weather.model.SolarFlareDaySummary
import javax.inject.Inject

class SolarFlareMapper @Inject constructor(
    private val dateParser: DateParser,
) {
    fun fromDto(dto: List<DonkiFLRDto>): List<SolarFlareDaySummary> {
        return dto
            .sortedByDescending { dateParser.parseFull(it.peakTime) }
            .groupBy { dateParser.parseFull(it.peakTime) }
            .mapNotNull { (date, list) ->
                val maxFlare = list.maxByOrNull { it.classType }
                val maxFlareClass = maxFlare?.classType
                SolarFlareDaySummary(
                    date = date?.prettyPrintShort() ?: return@mapNotNull null,
                    maxFlareClass = maxFlareClass.orEmpty(),
                    peakTimeOfMaxFlare = dateParser.parseToPrettyPrintTime(maxFlare?.peakTime)
                        .orEmpty(),
                    flareCount = list.size,
                )
            }
    }
}