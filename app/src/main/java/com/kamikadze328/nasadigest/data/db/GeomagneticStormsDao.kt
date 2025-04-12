package com.kamikadze328.nasadigest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kamikadze328.nasadigest.ui.features.weather.model.GeomagneticStormDaySummary

@Dao
interface GeomagneticStormsDao {
    @Query("SELECT * FROM geomagnetic_storms")
    suspend fun getAll(): List<GeomagneticStormDaySummary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<GeomagneticStormDaySummary>)

    @Query("DELETE FROM geomagnetic_storms")
    suspend fun deleteAll()
}