package com.kamikadze328.nasadigest.data.features.picturesoftheday

import com.kamikadze328.nasadigest.data.Result
import com.kamikadze328.nasadigest.data.SecretsRepository
import com.kamikadze328.nasadigest.data.client
import com.kamikadze328.nasadigest.data.get
import javax.inject.Inject

class ApodApiNetworkDataSource @Inject constructor(
    private val nasaSecretsRepository: SecretsRepository,
) {
    suspend fun fetchPictureOfTheDay(): Result<List<ApodDto>> {
        return client.get("https://science.nasa.gov/wp-json/wp/v2/apod-basic?api_key=${nasaSecretsRepository.nasaApiKey}")
    }
}