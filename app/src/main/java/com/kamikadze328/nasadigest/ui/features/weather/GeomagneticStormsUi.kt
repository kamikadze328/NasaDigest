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
import com.kamikadze328.nasadigest.ui.features.weather.model.GeomagneticStormDaySummary
import com.kamikadze328.nasadigest.ui.features.weather.model.GeomagneticStormsState
import kotlinx.collections.immutable.persistentListOf
import kotlin.math.roundToInt

@Composable
internal fun GeomagneticStormsUi(
    uiState: GeomagneticStormsState,
    onRefresh: () -> Unit,
) {
    when (uiState) {
        is GeomagneticStormsState.Loading -> {
            LoadingScreenUi(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp),
            )
        }

        is GeomagneticStormsState.Error -> {
            ErrorScreenUi(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.message,
                onRefresh = onRefresh,
            )
        }

        is GeomagneticStormsState.Data -> {
            GeomagneticStormsDataUi(
                uiState = uiState,
                onRefresh = onRefresh,
            )
        }
    }
}

@Composable
private fun GeomagneticStormsDataUi(
    uiState: GeomagneticStormsState.Data,
    onRefresh: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {
        GeomagneticStormTitleUi(
            onRefresh = onRefresh,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = uiState.data,
                key = { it.date },
            ) {
                GeomagneticStormDaySummaryUi(data = it)
            }
        }
    }
}

@Composable
private fun GeomagneticStormTitleUi(onRefresh: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.geomagnetic_storms_title),
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
private fun GeomagneticStormDaySummaryUi(
    data: GeomagneticStormDaySummary,
) {
    val background = when (data.maxKpIndex?.roundToInt()) {
        in 0..2 -> MaterialTheme.colorScheme.primaryContainer
        in 3..4 -> MaterialTheme.colorScheme.errorContainer
        in 5..6 -> MaterialTheme.colorScheme.primary
        in 7..8 -> MaterialTheme.colorScheme.tertiary
        in 8..10 -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primaryContainer
    }
    val textColor = when (data.maxKpIndex?.roundToInt()) {
        in 0..2 -> MaterialTheme.colorScheme.onPrimaryContainer
        in 3..4 -> MaterialTheme.colorScheme.onErrorContainer
        in 5..6 -> MaterialTheme.colorScheme.onPrimary
        in 7..8 -> MaterialTheme.colorScheme.onTertiary
        in 8..10 -> MaterialTheme.colorScheme.onError
        else -> MaterialTheme.colorScheme.onPrimaryContainer
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
        if (data.maxKpIndex != null) {
            Text(
                text = stringResource(R.string.geomagnetic_storms_max_kp, data.maxKpIndex),
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
            )
        }
        Text(
            text = stringResource(R.string.geomagnetic_storms_in_day_amount, data.kpCount),
            style = MaterialTheme.typography.bodySmall,
            color = textColor,
        )
    }
}

@Preview
@Composable
private fun GeomagneticStormsPreviewUi() {
    GeomagneticStormsUi(
        uiState = GeomagneticStormsState.Data(
            data = persistentListOf(
                GeomagneticStormDaySummary(
                    maxKpIndex = 9.7,
                    kpCount = 4,
                    date = "11 April"
                ),
                GeomagneticStormDaySummary(
                    maxKpIndex = 1.12,
                    kpCount = 1,
                    date = "12 April"
                )
            )
        ),
        onRefresh = {},
    )
}