package com.kamikadze328.nasadigest.data.features.weather

import com.kamikadze328.nasadigest.data.Result
import com.kamikadze328.nasadigest.data.SecretsRepository
import com.kamikadze328.nasadigest.data.client
import com.kamikadze328.nasadigest.data.get
import com.kamikadze328.nasadigest.data.features.weather.model.DonkiFLRDto
import com.kamikadze328.nasadigest.data.features.weather.model.DonkiGSTDto
import javax.inject.Inject

class DonkiApiNetworkDataSource @Inject constructor(
    private val secretsRepository: SecretsRepository,
) {
    private val nasaApiKey
        get() = secretsRepository.nasaApiKey

    suspend fun fetchGeomagneticStorm(): Result<List<DonkiGSTDto>> {
        return client.get("https://api.nasa.gov/DONKI/GST?api_key=$nasaApiKey")
    }

    suspend fun fetchSolarFlare(): Result<List<DonkiFLRDto>> {
        return client.get("https://api.nasa.gov/DONKI/FLR?api_key=$nasaApiKey")
    }
}