package com.kamikadze328.nasadigest.data.db

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NasaDigestDatabase @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    companion object {
        private val Context.db: AppDatabase
            get() = Room.databaseBuilder(this, AppDatabase::class.java, "database")
                .fallbackToDestructiveMigration(true)
                .build()
    }

    val geomagneticStormsDao: GeomagneticStormsDao
        get() = context.db.geomagneticStormsDao()
    val solarFlaresDao: SolarFlaresDao
        get() = context.db.solarFlaresDao()
}