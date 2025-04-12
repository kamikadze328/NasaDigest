package com.kamikadze328.nasadigest.data.features.picturesoftheday

import com.kamikadze328.nasadigest.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PictureOfTheDayRepository @Inject constructor(
    private val networkDataSource: ApodApiNetworkDataSource
) {
    suspend fun getPictureOfTheDay(): Result<ApodDto> {
        return withContext(Dispatchers.IO) {
            networkDataSource.fetchPictureOfTheDay()
        }
    }
}