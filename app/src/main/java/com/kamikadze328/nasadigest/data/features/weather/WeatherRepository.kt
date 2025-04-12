package com.kamikadze328.nasadigest.data.features.weather

import com.kamikadze328.nasadigest.data.CurrentDateRepository
import com.kamikadze328.nasadigest.data.Result
import com.kamikadze328.nasadigest.data.UpdateDatesDataSource
import com.kamikadze328.nasadigest.data.db.GeomagneticStormsDao
import com.kamikadze328.nasadigest.data.db.NasaDigestDatabase
import com.kamikadze328.nasadigest.data.db.SolarFlaresDao
import com.kamikadze328.nasadigest.ui.features.weather.model.GeomagneticStormDaySummary
import com.kamikadze328.nasadigest.ui.features.weather.model.SolarFlareDaySummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val networkDataSource: DonkiApiNetworkDataSource,
    private val datesUpdateDatesDataSource: UpdateDatesDataSource,
    private val currentDateRepository: CurrentDateRepository,
    private val geomagneticStormsMapper: GeomagneticStormsMapper,
    private val solarFlaresMapper: SolarFlareMapper,
    private val db: NasaDigestDatabase,
) {
    private val today: Long
        get() = currentDateRepository.today()

    private val geomagneticStormsLocalDataSource: GeomagneticStormsDao
        get() = db.geomagneticStormsDao
    private val solarFlaresLocalDataSource: SolarFlaresDao
        get() = db.solarFlaresDao

    suspend fun getGeomagneticStorm(shouldForceUpdate: Boolean): Result<List<GeomagneticStormDaySummary>> {
        return withContext(Dispatchers.IO) {
            if (!shouldForceUpdate && shouldUseLocalGeoMagneticStormsData()) {
                val cached = geomagneticStormsLocalDataSource.getAll()
                if (cached.isNotEmpty()) {
                    return@withContext Result.Success(cached)
                }
            }

            val result = when (val dto = networkDataSource.fetchGeomagneticStorm()) {
                is Result.Success -> Result.Success(geomagneticStormsMapper.fromDto(dto.data))
                is Result.Error -> Result.Error(dto.throwable)
            }

            if (result is Result.Success) {
                geomagneticStormsLocalDataSource.deleteAll()
                geomagneticStormsLocalDataSource.insertAll(result.data)
                datesUpdateDatesDataSource.updateGeomagneticStormsLastUpdate(today)
            }
            result
        }
    }

    suspend fun getSolarFlare(shouldForceUpdate: Boolean): Result<List<SolarFlareDaySummary>> {
        return withContext(Dispatchers.IO) {
            if (!shouldForceUpdate && shouldUseLocalSolarFlareData()) {
                val cached = solarFlaresLocalDataSource.getAll()
                if (cached.isNotEmpty()) {
                    return@withContext Result.Success(cached)
                }
            }

            val result = when (val dto = networkDataSource.fetchSolarFlare()) {
                is Result.Success -> Result.Success(solarFlaresMapper.fromDto(dto.data))
                is Result.Error -> Result.Error(dto.throwable)
            }

            if (result is Result.Success) {
                solarFlaresLocalDataSource.deleteAll()
                solarFlaresLocalDataSource.insertAll(result.data)
                datesUpdateDatesDataSource.updateSolarFlareLastUpdate(today)
            }
            result
        }
    }

    private fun wasDataFetchToday(lastUpdateDate: Long): Boolean {
        return currentDateRepository.makeStartOfDay(lastUpdateDate) == today
    }

    private suspend fun shouldUseLocalGeoMagneticStormsData(): Boolean {
        val updateDate = datesUpdateDatesDataSource.getGeomagneticStormsLastUpdate()
        return updateDate != null && wasDataFetchToday(updateDate)
    }

    private suspend fun shouldUseLocalSolarFlareData(): Boolean {
        val updateDate = datesUpdateDatesDataSource.getSolarFlareLastUpdate()
        return updateDate != null && wasDataFetchToday(updateDate)
    }
}