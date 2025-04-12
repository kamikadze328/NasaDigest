package com.kamikadze328.nasadigest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kamikadze328.nasadigest.ui.features.weather.model.SolarFlareDaySummary

@Dao
interface SolarFlaresDao {
    @Query("SELECT * FROM solar_flares")
    suspend fun getAll(): List<SolarFlareDaySummary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<SolarFlareDaySummary>)

    @Query("DELETE FROM solar_flares")
    suspend fun deleteAll()
}