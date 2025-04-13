package com.kamikadze328.nasadigest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kamikadze328.nasadigest.ui.features.weather.model.GeomagneticStormDaySummary
import com.kamikadze328.nasadigest.ui.features.weather.model.SolarFlareDaySummary

@Database(
    entities = [
        GeomagneticStormDaySummary::class,
        SolarFlareDaySummary::class
    ],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun geomagneticStormsDao(): GeomagneticStormsDao
    abstract fun solarFlaresDao(): SolarFlaresDao
}