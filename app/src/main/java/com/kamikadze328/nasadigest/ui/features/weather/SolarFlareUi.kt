package com.kamikadze328.nasadigest.ui.features.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kamikadze328.nasadigest.R
import com.kamikadze328.nasadigest.ui.common.ErrorScreenUi
import com.kamikadze328.nasadigest.ui.common.LoadingScreenUi
import com.kamikadze328.nasadigest.ui.features.weather.model.SolarFlareDaySummary
import com.kamikadze328.nasadigest.ui.features.weather.model.SolarFlareState
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun SolarFlareUi(
    uiState: SolarFlareState,
    onRefresh: () -> Unit,
) {
    when (uiState) {
        is SolarFlareState.Loading -> {
            LoadingScreenUi(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp),
            )
        }

        is SolarFlareState.Error -> {
            ErrorScreenUi(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.message,
                onRefresh = onRefresh,
            )
        }

        is SolarFlareState.Data -> {
            SolarFlareDataUi(
                uiState = uiState,
                onRefresh = onRefresh,
            )
        }
    }
}

@Composable
private fun SolarFlareDataUi(
    uiState: SolarFlareState.Data,
    onRefresh: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {
        SolarFlareTitleUi(
            onRefresh = onRefresh,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = uiState.data,
                key = { it.date },
            ) {
                SolarFlareDaySummaryUi(data = it)
            }
        }
    }
}

@Composable
private fun SolarFlareTitleUi(onRefresh: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.solar_flares_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Box(
            modifier = Modifier
                .clickable { onRefresh.invoke() },
        ) {
            Image(
                modifier = Modifier
                    .padding(12.dp)
                    .size(24.dp),
                imageVector = Icons.Default.Refresh,
                contentDescription = stringResource(R.string.refresh),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }
    }
}

@Composable
private fun SolarFlareDaySummaryUi(
    data: SolarFlareDaySummary,
) {
    val background = when (data.maxFlareClassName) {
        SolarFlareDaySummary.Class.X -> MaterialTheme.colorScheme.error
        SolarFlareDaySummary.Class.M -> MaterialTheme.colorScheme.tertiary
        SolarFlareDaySummary.Class.C -> MaterialTheme.colorScheme.primary
        SolarFlareDaySummary.Class.B -> MaterialTheme.colorScheme.errorContainer
        SolarFlareDaySummary.Class.A -> MaterialTheme.colorScheme.primaryContainer
    }
    val textColor = when (data.maxFlareClassName) {
        SolarFlareDaySummary.Class.X -> MaterialTheme.colorScheme.onError
        SolarFlareDaySummary.Class.M -> MaterialTheme.colorScheme.onTertiary
        SolarFlareDaySummary.Class.C -> MaterialTheme.colorScheme.onPrimary
        SolarFlareDaySummary.Class.B -> MaterialTheme.colorScheme.onErrorContainer
        SolarFlareDaySummary.Class.A -> MaterialTheme.colorScheme.onPrimaryContainer
    }
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(background)
            .padding(8.dp),
    ) {
        Text(
            text = data.date,
            style = MaterialTheme.typography.titleSmall,
            color = textColor,
        )
        Text(
            text = stringResource(R.string.solar_flares_max_class, data.maxFlareClass),
            style = MaterialTheme.typography.bodyMedium,
            color = textColor,
        )
        Text(
            text = stringResource(R.string.solar_flares_in_day_amount, data.flareCount),
            style = MaterialTheme.typography.bodySmall,
            color = textColor,
        )
        Text(
            text = stringResource(R.string.solar_flares_peak_time, data.peakTimeOfMaxFlare),
            style = MaterialTheme.typography.bodySmall,
            color = textColor,
        )
    }
}

@Preview
@Composable
private fun SolarFlarePreviewUi() {
    SolarFlareUi(
        uiState = SolarFlareState.Data(
            data = persistentListOf(
                SolarFlareDaySummary(
                    maxFlareClass = "X1.0",
                    flareCount = 5,
                    peakTimeOfMaxFlare = "12:00:00",
                    date = "Fri 11 April"
                ),
                SolarFlareDaySummary(
                    maxFlareClass = "M1.0",
                    flareCount = 3,
                    peakTimeOfMaxFlare = "14:00:00",
                    date = "Fri 12 April"
                )
            )
        ),
        onRefresh = {},
    )
}