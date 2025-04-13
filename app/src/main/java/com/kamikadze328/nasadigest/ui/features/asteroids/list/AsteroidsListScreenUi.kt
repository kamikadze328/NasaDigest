package com.kamikadze328.nasadigest.ui.features.asteroids.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kamikadze328.nasadigest.R
import com.kamikadze328.nasadigest.ui.common.ErrorScreenUi
import com.kamikadze328.nasadigest.ui.common.LoadingScreenUi
import com.kamikadze328.nasadigest.ui.common.prettyPrint
import com.kamikadze328.nasadigest.ui.features.asteroids.info.model.AsteroidInfoArgs
import com.kamikadze328.nasadigest.ui.features.asteroids.list.model.AsteroidInListUi
import com.kamikadze328.nasadigest.ui.features.asteroids.list.model.AsteroidListUi
import com.kamikadze328.nasadigest.ui.features.asteroids.list.model.AsteroidsListUiState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import org.joda.time.LocalDate

@Composable
fun AsteroidsListScreenUi(
    viewModel: AsteroidsListViewModel = hiltViewModel(),
    navigateToAsteroidInfo: (AsteroidInfoArgs) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    AsteroidsListScreenUi(
        uiState = uiState,
        onEvent = remember { viewModel::onEvent },
        navigateToAsteroidInfo = navigateToAsteroidInfo,
    )
}

@Composable
private fun AsteroidsListScreenUi(
    uiState: AsteroidsListUiState,
    onEvent: (AsteroidsListUiEvent) -> Unit,
    navigateToAsteroidInfo: (AsteroidInfoArgs) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        when (uiState) {
            is AsteroidsListUiState.Loading -> {
                LoadingScreenUi(modifier = Modifier.fillMaxSize())
            }

            is AsteroidsListUiState.Error -> {
                ErrorScreenUi(
                    modifier = Modifier.fillMaxSize(),
                    text = uiState.error,
                    onRefresh = { onEvent.invoke(AsteroidsListUiEvent.OnRefresh) },
                )
            }

            is AsteroidsListUiState.Success -> {
                AsteroidsListUi(
                    uiState = uiState,
                    navigateToAsteroidInfo = navigateToAsteroidInfo,
                )
            }
        }
    }

}

@Composable
private fun AsteroidsListUi(
    uiState: AsteroidsListUiState.Success,
    navigateToAsteroidInfo: (AsteroidInfoArgs) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            uiState.data.data.forEach {
                val date = it.key
                val asteroids = it.value
                item(key = date) {
                    ItemDateUi(
                        date = date,
                        hazardousAsteroidsAmount = asteroids.count { asteroid ->
                            asteroid.isPotentiallyHazardousAsteroid
                        },
                    )
                }
                items(
                    items = asteroids,
                    key = { asteroid -> asteroid.id }
                ) { asteroid ->
                    ItemAsteroidUi(
                        asteroid = asteroid,
                        onClick = {
                            navigateToAsteroidInfo.invoke(
                                AsteroidInfoArgs(asteroidId = asteroid.id)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemDateUi(date: String, hazardousAsteroidsAmount: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp),
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        val amountText = if (hazardousAsteroidsAmount == 0) {
            stringResource(id = R.string.asteroid_hazardous_amount_zero)
        } else {
            pluralStringResource(
                id = R.plurals.asteroid_hazardous_amount,
                hazardousAsteroidsAmount,
                hazardousAsteroidsAmount
            )
        }
        Text(
            text = amountText,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

    }
}

@Composable
private fun ItemAsteroidUi(asteroid: AsteroidInListUi, onClick: () -> Unit) {
    val backgroundColor = if (asteroid.isPotentiallyHazardousAsteroid) {
        MaterialTheme.colorScheme.tertiary
    } else {
        MaterialTheme.colorScheme.background
    }
    val textColor = if (asteroid.isPotentiallyHazardousAsteroid) {
        MaterialTheme.colorScheme.onTertiary
    } else {
        MaterialTheme.colorScheme.onBackground
    }
    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = asteroid.name,
                    style = MaterialTheme.typography.labelLarge,
                    color = textColor,
                )
                Spacer(modifier = Modifier.weight(1.0f))
                if (asteroid.isPotentiallyHazardousAsteroid) {
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = stringResource(R.string.asteroid_potentially_hazardous),
                        style = MaterialTheme.typography.labelMedium,
                        color = textColor
                    )
                    Image(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(16.dp)
                    )
                }
            }
            Text(
                text = stringResource(
                    id = R.string.asteroid_diameter_km,
                    asteroid.diameterMin?.toString().orEmpty(),
                    asteroid.diameterMax?.toString().orEmpty()
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
            )
            Text(
                text = stringResource(
                    id = R.string.asteroid_absolute_magnitude,
                    asteroid.absoluteMagnitudeH?.toString().orEmpty()
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
            )
        }
    }
}


@Preview
@Composable
private fun AsteroidsListScreenUiPreview() {
    AsteroidsListScreenUi(
        uiState = AsteroidsListUiState.Success(
            data = AsteroidListUi(
                data = persistentMapOf(
                    LocalDate().prettyPrint() to persistentListOf(
                        AsteroidInListUi(
                            id = "1",
                            name = "Asteroid 1",
                            diameterMin = 0.5,
                            diameterMax = 1.0,
                            isPotentiallyHazardousAsteroid = true,
                            absoluteMagnitudeH = 22.0
                        ),
                        AsteroidInListUi(
                            id = "2",
                            name = "Asteroid 2",
                            diameterMin = 0.4,
                            diameterMax = 2.0,
                            isPotentiallyHazardousAsteroid = false,
                            absoluteMagnitudeH = 412.0
                        ),
                        AsteroidInListUi(
                            id = "3",
                            name = "Asteroid 3",
                            diameterMin = 0.5,
                            diameterMax = 1.0,
                            isPotentiallyHazardousAsteroid = false,
                            absoluteMagnitudeH = 12.0
                        )
                    ),
                    LocalDate().plusDays(1).prettyPrint() to persistentListOf(
                        AsteroidInListUi(
                            id = "7",
                            name = "Asteroid7",
                            diameterMin = 123.5,
                            diameterMax = 2123.0,
                            isPotentiallyHazardousAsteroid = true,
                            absoluteMagnitudeH = 123.0
                        )
                    ),
                    LocalDate().plusDays(2).prettyPrint() to persistentListOf(
                        AsteroidInListUi(
                            id = "6",
                            name = "Asteroid 6",
                            diameterMin = 0.1235,
                            diameterMax = 1.0123,
                            isPotentiallyHazardousAsteroid = false,
                            absoluteMagnitudeH = 21232.0
                        )
                    ),
                )
            )
        ),
        onEvent = {},
        navigateToAsteroidInfo = {},
    )
}