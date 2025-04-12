package com.kamikadze328.nasadigest.data

import com.kamikadze328.nasadigest.BuildConfig
import javax.inject.Inject

/**
 * Provides secrets for the application.
 */
class SecretsRepository @Inject constructor() {
    val nasaApiKey get() = BuildConfig.NASA_API_KEY
}