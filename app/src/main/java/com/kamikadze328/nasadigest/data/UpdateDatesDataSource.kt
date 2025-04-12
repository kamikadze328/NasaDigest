package com.kamikadze328.nasadigest.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpdateDatesDataSource @Inject constructor(
    @ApplicationContext
    private val context: Context,
) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dates")
        private val GEOMAGNETIC_STORM_LAST_UPDATE =
            longPreferencesKey("geomagnetic_storm_last_update")
        private val SOLAR_FLARE_LAST_UPDATE =
            longPreferencesKey("solar_flare_last_update")
    }

    private suspend fun preferences(): Preferences = context.dataStore.data.first()

    suspend fun getGeomagneticStormsLastUpdate(): Long? {
        return preferences()[GEOMAGNETIC_STORM_LAST_UPDATE]
    }

    suspend fun updateGeomagneticStormsLastUpdate(date: Long) {
        context.dataStore.edit { preferences ->
            preferences[GEOMAGNETIC_STORM_LAST_UPDATE] = date
        }
    }

    suspend fun getSolarFlareLastUpdate(): Long? {
        return preferences()[SOLAR_FLARE_LAST_UPDATE]
    }

    suspend fun updateSolarFlareLastUpdate(date: Long) {
        context.dataStore.edit { preferences ->
            preferences[SOLAR_FLARE_LAST_UPDATE] = date
        }
    }
}