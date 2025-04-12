package com.kamikadze328.nasadigest.ui.features.pictureoftheday.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface PictureOfTheDayUiState {
    data object Loading : PictureOfTheDayUiState
    data class Error(val message: String?) : PictureOfTheDayUiState

    @Immutable
    data class Success(
        val title: String?,
        val explanation: String?,
        val url: String,
        val date: String,
        val copyright: String?,
    ) : PictureOfTheDayUiState
}