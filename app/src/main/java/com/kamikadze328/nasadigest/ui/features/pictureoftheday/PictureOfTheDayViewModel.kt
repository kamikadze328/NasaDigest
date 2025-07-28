package com.kamikadze328.nasadigest.ui.features.pictureoftheday

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamikadze328.nasadigest.data.Result
import com.kamikadze328.nasadigest.data.features.picturesoftheday.ApodDto
import com.kamikadze328.nasadigest.data.features.picturesoftheday.PictureOfTheDayRepository
import com.kamikadze328.nasadigest.ui.common.DateParser
import com.kamikadze328.nasadigest.ui.features.pictureoftheday.model.PictureOfTheDayUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.joda.time.LocalDate
import javax.inject.Inject

sealed interface PictureOfTheDayUiEvent {
    data object OnRefresh : PictureOfTheDayUiEvent
}

@HiltViewModel
class PictureOfTheDayViewModel @Inject constructor(
    private val repository: PictureOfTheDayRepository,
    private val dateParser: DateParser,
) : ViewModel() {
    companion object {
        private const val TAG = "PictureOfTheDayViewModel"
    }

    private val _uiState: MutableStateFlow<PictureOfTheDayUiState> =
        MutableStateFlow(PictureOfTheDayUiState.Loading)
    val uiState: StateFlow<PictureOfTheDayUiState> = _uiState.asStateFlow()

    init {
        fetchPictureOfTheDay()
    }

    fun onEvent(event: PictureOfTheDayUiEvent) {
        when (event) {
            is PictureOfTheDayUiEvent.OnRefresh -> onRefresh()
        }
    }

    private fun onRefresh() {
        fetchPictureOfTheDay()
    }

    private fun fetchPictureOfTheDay() {
        _uiState.value = PictureOfTheDayUiState.Loading

        viewModelScope.launch {
            val result = runCatching { repository.getPictureOfTheDay() }
                .mapCatching {
                    when (it) {
                        is Result.Success -> Result.Success(it.data.toUi())
                        is Result.Error -> Result.Error(it.throwable)
                    }
                }
                .getOrElse { Result.Error(it) }

            when (result) {
                is Result.Error -> onError(result.throwable)
                is Result.Success -> onSuccess(result.data)
            }
        }
    }

    private fun onError(throwable: Throwable) {
        Log.e(TAG, "An error during fetching picture of the day: ${throwable.message}", throwable)
        _uiState.value = PictureOfTheDayUiState.Error(throwable.message)
    }

    private fun onSuccess(uiState: PictureOfTheDayUiState) {
        _uiState.value = uiState
    }

    private fun ApodDto.toUi(): PictureOfTheDayUiState {
        if (title == null) {
            return PictureOfTheDayUiState.Error(null)
        }
        return PictureOfTheDayUiState.Success(
            title = title,
            explanation = explanation,
            url = url,
            copyright = copyright,
            mediaType = when (mediaType) {
                ApodDto.MEDIA_TYPE_IMAGE -> PictureOfTheDayUiState.Success.MediaType.IMAGE
                ApodDto.MEDIA_TYPE_VIDEO -> PictureOfTheDayUiState.Success.MediaType.VIDEO
                else -> PictureOfTheDayUiState.Success.MediaType.OTHER
            },
            date = dateParser.parse(date) ?: LocalDate()
        )
    }
}

