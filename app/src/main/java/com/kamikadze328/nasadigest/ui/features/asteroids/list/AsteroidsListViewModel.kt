package com.kamikadze328.nasadigest.ui.features.asteroids.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamikadze328.nasadigest.data.Result
import com.kamikadze328.nasadigest.data.features.asteroids.AsteroidsRepository
import com.kamikadze328.nasadigest.data.features.asteroids.model.NeoFeedAsteroidDto
import com.kamikadze328.nasadigest.data.features.asteroids.model.NeoFeedDto
import com.kamikadze328.nasadigest.ui.common.DateParser
import com.kamikadze328.nasadigest.ui.common.cut
import com.kamikadze328.nasadigest.ui.features.asteroids.list.model.AsteroidInListUi
import com.kamikadze328.nasadigest.ui.features.asteroids.list.model.AsteroidListUi
import com.kamikadze328.nasadigest.ui.features.asteroids.list.model.AsteroidsListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AsteroidsListViewModel @Inject constructor(
    private val asteroidsRepository: AsteroidsRepository,
    private val dateParser: DateParser,
) : ViewModel() {
    companion object {
        private const val TAG = "AsteroidsListViewModel"
    }

    private val _uiState: MutableStateFlow<AsteroidsListUiState> =
        MutableStateFlow(AsteroidsListUiState.Loading)
    val uiState: StateFlow<AsteroidsListUiState> = _uiState.asStateFlow()

    init {
        getAsteroidList()
    }

    fun onEvent(event: AsteroidsListUiEvent) {
        when (event) {
            AsteroidsListUiEvent.OnRefresh -> onRefresh()
        }
    }

    private fun onRefresh() {
        getAsteroidList()
    }

    private fun getAsteroidList() {
        _uiState.value = AsteroidsListUiState.Loading

        viewModelScope.launch {
            val result = runCatching { asteroidsRepository.getAsteroidsList() }
                .mapCatching {
                    when (it) {
                        is Result.Success -> Result.Success(it.data.toUi())
                        is Result.Error -> Result.Error(it.throwable)
                    }
                }
                .getOrElse { Result.Error(it) }

            when (result) {
                is Result.Success -> onSuccess(result.data)
                is Result.Error -> onError(result.throwable)
            }
        }
    }

    private fun onSuccess(uiState: AsteroidListUi) {
        _uiState.value = AsteroidsListUiState.Success(uiState)
    }

    private fun onError(throwable: Throwable) {
        Log.e(TAG, "An error during fetching asteroids: ${throwable.message}", throwable)

        _uiState.value = AsteroidsListUiState.Error(throwable.message)
    }

    private fun NeoFeedDto.toUi(): AsteroidListUi {
        val uiData = nearEarthObjects.mapNotNull {
            val date = dateParser.parseToPrettyPrint(it.key) ?: return@mapNotNull null
            val uiList = it.value.mapNotNull { asteroid -> asteroid.toUi() }.toImmutableList()

            date to uiList
        }.toMap().toImmutableMap()

        return AsteroidListUi(
            data = uiData
        )
    }

    private fun NeoFeedAsteroidDto.toUi(): AsteroidInListUi? {
        return AsteroidInListUi(
            id = id ?: return null,
            name = name.orEmpty(),
            diameterMin = estimatedDiameter?.kilometers?.estimatedDiameterMin?.cut(),
            diameterMax = estimatedDiameter?.kilometers?.estimatedDiameterMax?.cut(),
            isPotentiallyHazardousAsteroid = isPotentiallyHazardousAsteroid == true,
            absoluteMagnitudeH = absoluteMagnitudeH?.cut(),
        )
    }
}

