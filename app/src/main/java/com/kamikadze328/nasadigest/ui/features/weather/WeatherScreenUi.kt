package com.kamikadze328.nasadigest.ui.features.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.kamikadze328.nasadigest.ui.features.weather.model.*
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun WeatherScreenUi(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    WeatherScreenUi(
        uiState = uiState,
        onEvent = remember { viewModel::onEvent }
    )
}

@Composable
private fun WeatherScreenUi(
    uiState: WeatherUiState,
    onEvent: (WeatherUiEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        SolarFlareUi(
            uiState = uiState.solarFlareState,
            onRefresh = { onEvent(WeatherUiEvent.OnRefreshSolarFlare) },
        )
        GeomagneticStormsUi(
            uiState = uiState.geomagneticStormsState,
            onRefresh = { onEvent(WeatherUiEvent.OnRefreshGeomagneticStorm) },
        )
    }
}


@Preview
@Composable
private fun WeatherScreenUiPreview() {
    WeatherScreenUi(
        uiState = WeatherUiState(
            solarFlareState = SolarFlareState.Data(
                data = persistentListOf(
                    SolarFlareDaySummary(
                        maxFlareClassFull = "X1.0",
                        maxFlareClass = SolarFlareDaySummary.Class.X,
                        flareCount = 5,
                        peakTimeOfMaxFlare = "12:00:00",
                        date = "Fri 11 April"
                    ),
                    SolarFlareDaySummary(
                        maxFlareClassFull = "M1.0",
                        maxFlareClass = SolarFlareDaySummary.Class.M,
                        flareCount = 3,
                        peakTimeOfMaxFlare = "14:00:00",
                        date = "Fri 12 April"
                    )
                )
            ),
            geomagneticStormsState = GeomagneticStormsState.Data(
                data = persistentListOf(
                    GeomagneticStormDaySummary(
                        maxKpIndex = 9.7,
                        kpCount = 4,
                        date = "11 April"
                    ),
                    GeomagneticStormDaySummary(
                        maxKpIndex = 3.01,
                        kpCount = 1,
                        date = "12 April"
                    ),
                )
            )
        ),
        onEvent = {},
    )
}