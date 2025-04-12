package com.kamikadze328.nasadigest.data.features.asteroids

import com.kamikadze328.nasadigest.data.Result
import com.kamikadze328.nasadigest.data.features.asteroids.model.NeoFeedDto
import com.kamikadze328.nasadigest.data.features.asteroids.model.NeoLookupAsteroidDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AsteroidsRepository @Inject constructor(
    private val networkDataSource: NeoApiNetworkDataSource
) {
    suspend fun getAsteroidsList(): Result<NeoFeedDto> = withContext(Dispatchers.IO) {
        networkDataSource.fetchAsteroidsList()
    }

    suspend fun getAsteroidDetails(id: String): Result<NeoLookupAsteroidDto> {
        return withContext(Dispatchers.IO) {
            networkDataSource.fetchAsteroidDetails(id)
        }
    }
}