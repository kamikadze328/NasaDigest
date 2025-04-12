package com.kamikadze328.nasadigest.ui.features.asteroids.info

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamikadze328.nasadigest.data.Result
import com.kamikadze328.nasadigest.data.features.asteroids.AsteroidsRepository
import com.kamikadze328.nasadigest.data.features.asteroids.model.NeoLookupAsteroidDto
import com.kamikadze328.nasadigest.ui.common.DateParser
import com.kamikadze328.nasadigest.ui.common.cut
import com.kamikadze328.nasadigest.ui.navigation.Destination
import com.kamikadze328.nasadigest.ui.features.asteroids.info.model.AsteroidInfoUi
import com.kamikadze328.nasadigest.ui.features.asteroids.info.model.AsteroidInfoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AsteroidInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val asteroidsRepository: AsteroidsRepository,
    private val dateParser: DateParser,
) : ViewModel() {
    companion object {
        private const val TAG = "AsteroidInfoViewModel"
    }

    private val asteroidId: String =
        requireNotNull(savedStateHandle.get<String>(Destination.Asteroids.Destination.Info.ASTEROID_ID_KEY)) {
            "Asteroid ID is required"
        }

    private val _uiState: MutableStateFlow<AsteroidInfoUiState> =
        MutableStateFlow(AsteroidInfoUiState.Loading)
    val uiState: StateFlow<AsteroidInfoUiState> = _uiState.asStateFlow()

    init {
        getAsteroidInfo()
    }

    fun onEvent(event: AsteroidInfoUiEvent) {
        when (event) {
            is AsteroidInfoUiEvent.OnRefresh -> onRefresh()
        }
    }

    private fun getAsteroidInfo() {
        _uiState.value = AsteroidInfoUiState.Loading

        viewModelScope.launch {
            val result = runCatching { asteroidsRepository.getAsteroidDetails(asteroidId) }
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
        Log.e(TAG, "Error fetching asteroid data", throwable)
        _uiState.value = AsteroidInfoUiState.Error(
            message = throwable.message,
        )
    }

    private fun onSuccess(uiState: AsteroidInfoUi) {
        _uiState.value = AsteroidInfoUiState.Success(asteroid = uiState)
    }

    private fun NeoLookupAsteroidDto.toUi(): AsteroidInfoUi {
        return AsteroidInfoUi(
            id = requireNotNull(id),
            name = name.orEmpty(),
            url = url,
            diameterMin = diameter?.kilometers?.estimatedDiameterMin?.cut(),
            diameterMax = diameter?.kilometers?.estimatedDiameterMax?.cut(),
            isPotentiallyHazardousAsteroid = isPotentiallyHazardousAsteroid == true,
            absoluteMagnitudeH = absoluteMagnitudeH?.cut(),
            firstObservationDate = dateParser.parse(orbitalData?.firstObservationDate)?.toString(),
            orbitalClassType = orbitalData?.orbitClass?.classType,
            orbitalClassDescription = orbitalData?.orbitClass?.description,
        )
    }

    private fun onRefresh() {
        getAsteroidInfo()
    }
}

