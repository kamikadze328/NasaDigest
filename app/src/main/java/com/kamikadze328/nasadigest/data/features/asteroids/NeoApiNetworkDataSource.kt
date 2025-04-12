package com.kamikadze328.nasadigest.data.features.asteroids

import com.kamikadze328.nasadigest.data.Result
import com.kamikadze328.nasadigest.data.SecretsRepository
import com.kamikadze328.nasadigest.data.features.asteroids.model.NeoFeedDto
import com.kamikadze328.nasadigest.data.features.asteroids.model.NeoLookupAsteroidDto
import com.kamikadze328.nasadigest.data.client
import com.kamikadze328.nasadigest.data.get
import javax.inject.Inject

class NeoApiNetworkDataSource @Inject constructor(
    private val secretsRepository: SecretsRepository,
) {
    private val apiKey: String get() = secretsRepository.nasaApiKey

    suspend fun fetchAsteroidsList(): Result<NeoFeedDto> {
        return client.get("https://api.nasa.gov/neo/rest/v1/feed?api_key=$apiKey")
    }

    suspend fun fetchAsteroidDetails(id: String): Result<NeoLookupAsteroidDto> {
        return client.get("https://api.nasa.gov/neo/rest/v1/neo/$id?api_key=$apiKey")
    }
}