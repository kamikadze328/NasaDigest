package com.kamikadze328.nasadigest.ui.features.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamikadze328.nasadigest.data.Result
import com.kamikadze328.nasadigest.data.features.weather.WeatherRepository
import com.kamikadze328.nasadigest.ui.features.weather.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {
    companion object {
        private const val TAG = "WeatherViewModel"
    }

    private val _uiState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    init {
        initAllData()
    }

    private fun initAllData() {
        updateGeomagneticStorm()
        updateSolarFlare()
    }

    private fun updateGeomagneticStorm(shouldForceUpdate: Boolean = false) {
        _uiState.value =
            _uiState.value.copy(geomagneticStormsState = GeomagneticStormsState.Loading)
        viewModelScope.launch {
            val result = runCatching { repository.getGeomagneticStorm(shouldForceUpdate) }
                .getOrElse { Result.Error(it) }

            when (result) {
                is Result.Error -> onGeomagneticStormError(result.throwable)
                is Result.Success -> onGeomagneticStormSuccess(result.data)
            }
        }
    }

    private fun onGeomagneticStormError(throwable: Throwable) {
        _uiState.value = _uiState.value.copy(
            geomagneticStormsState = GeomagneticStormsState.Error(
                message = throwable.message,
            )
        )

        Log.e(TAG, "Error fetching geomagnetic storm data", throwable)
    }

    private fun onGeomagneticStormSuccess(uiState: List<GeomagneticStormDaySummary>) {
        _uiState.value = _uiState.value.copy(
            geomagneticStormsState = GeomagneticStormsState.Data(
                data = uiState.toImmutableList(),
            )
        )
    }

    private fun updateSolarFlare(shouldForceUpdate: Boolean = false) {
        _uiState.value = _uiState.value.copy(solarFlareState = SolarFlareState.Loading)
        viewModelScope.launch {
            val result = runCatching { repository.getSolarFlare(shouldForceUpdate) }
                .getOrElse { Result.Error(it) }

            when (result) {
                is Result.Error -> onSolarFlareError(result.throwable)
                is Result.Success -> onSolarFlareSuccess(result.data)
            }
        }
    }

    private fun onSolarFlareError(throwable: Throwable) {
        _uiState.value = _uiState.value.copy(
            solarFlareState = SolarFlareState.Error(
                message = throwable.message,
            )
        )

        Log.e(TAG, "Error fetching solar flare data", throwable)
    }

    private fun onSolarFlareSuccess(uiState: List<SolarFlareDaySummary>) {
        _uiState.value = _uiState.value.copy(
            solarFlareState = SolarFlareState.Data(
                data = uiState.toImmutableList(),
            )
        )
    }

    fun onEvent(event: WeatherUiEvent) {
        when (event) {
            WeatherUiEvent.OnRefreshGeomagneticStorm -> onRefreshGeomagneticStorm()
            WeatherUiEvent.OnRefreshSolarFlare -> onRefreshSolarFlare()
        }
    }

    private fun onRefreshGeomagneticStorm() {
        updateGeomagneticStorm(shouldForceUpdate = true)
    }

    private fun onRefreshSolarFlare() {
        updateSolarFlare(shouldForceUpdate = true)
    }
}