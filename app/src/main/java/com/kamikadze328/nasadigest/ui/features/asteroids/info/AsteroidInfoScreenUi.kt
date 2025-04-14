package com.kamikadze328.nasadigest.ui.features.asteroids.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kamikadze328.nasadigest.R
import com.kamikadze328.nasadigest.ui.common.ErrorScreenUi
import com.kamikadze328.nasadigest.ui.common.LoadingScreenUi
import com.kamikadze328.nasadigest.ui.features.asteroids.info.model.AsteroidInfoUi
import com.kamikadze328.nasadigest.ui.features.asteroids.info.model.AsteroidInfoUiState
import com.kamikadze328.nasadigest.ui.theme.NasaDigestTheme
import java.util.Date

@Composable
fun AsteroidInfoScreenUi(
    viewModel: AsteroidInfoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    AsteroidInfoScreenUi(
        uiState = uiState,
        onEvent = remember { viewModel::onEvent },
    )
}

@Composable
private fun AsteroidInfoScreenUi(
    uiState: AsteroidInfoUiState,
    onEvent: (AsteroidInfoUiEvent) -> Unit,
) {
    when (uiState) {
        is AsteroidInfoUiState.Loading -> {
            LoadingScreenUi(modifier = Modifier.fillMaxSize())
        }

        is AsteroidInfoUiState.Error -> {
            ErrorScreenUi(
                modifier = Modifier.fillMaxSize(),
                text = uiState.message,
                onRefresh = { onEvent(AsteroidInfoUiEvent.OnRefresh) },
            )
        }

        is AsteroidInfoUiState.Success -> {
            AsteroidInfoUi(asteroid = uiState.asteroid)
        }
    }
}

@Composable
private fun AsteroidInfoUi(asteroid: AsteroidInfoUi) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (asteroid.isPotentiallyHazardousAsteroid) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.background)
                    .padding(6.dp),
                text = asteroid.name,
                style = MaterialTheme.typography.headlineLarge,
                color = if (asteroid.isPotentiallyHazardousAsteroid) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = stringResource(id = R.string.asteroid_id, asteroid.id),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
            )

            if (asteroid.isPotentiallyHazardousAsteroid) {
                Text(
                    text = stringResource(R.string.asteroid_potentially_hazardous_full),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                )
            }

            if (asteroid.firstObservationDate != null) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = stringResource(
                        R.string.asteroid_first_observation_date,
                        asteroid.firstObservationDate
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            if (asteroid.orbitalClassType != null) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = stringResource(
                        R.string.asteroid_orbital_class_full,
                        asteroid.orbitalClassType,
                        asteroid.orbitalClassDescription.orEmpty()
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            Text(
                text = stringResource(
                    id = R.string.asteroid_diameter_km_full,
                    asteroid.diameterMin?.toString().orEmpty(),
                    asteroid.diameterMax?.toString().orEmpty()
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = stringResource(
                    id = R.string.asteroid_absolute_magnitude_full,
                    asteroid.absoluteMagnitudeH?.toString().orEmpty()
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )

            if (asteroid.url != null) {
                val uriHandler = LocalUriHandler.current
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    onClick = { uriHandler.openUri(asteroid.url) }
                ) {
                    Image(
                        modifier = Modifier.padding(end = 8.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = asteroid.url,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                    )
                    Text(
                        text = stringResource(id = R.string.asteroid_open_additional_info),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AsteroidInfoScreenUiPreview() {
    NasaDigestTheme {
        AsteroidInfoScreenUi(
            uiState = AsteroidInfoUiState.Success(
                asteroid = AsteroidInfoUi(
                    id = "12345",
                    name = "Asteroid 12345",
                    diameterMin = 0.5,
                    diameterMax = 1.5,
                    isPotentiallyHazardousAsteroid = true,
                    firstObservationDate = Date().toString(),
                    absoluteMagnitudeH = 22.0,
                    orbitalClassDescription = "Apollo",
                    orbitalClassType = "A",
                    url = "https://example.com/asteroid/12345",
                )
            ),
            onEvent = {}
        )
    }
}