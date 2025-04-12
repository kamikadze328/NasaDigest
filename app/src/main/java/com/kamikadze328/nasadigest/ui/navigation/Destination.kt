package com.kamikadze328.nasadigest.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.outlined.Warning
import com.kamikadze328.nasadigest.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface Destination {
    companion object {
        val topLevelRoutes = listOf(
            TopLevelRoute(
                nameResId = R.string.weather_title,
                route = Weather,
                icon = Icons.Outlined.LocationOn,
                selectedIcon = Icons.Filled.LocationOn
            ),
            TopLevelRoute(
                nameResId = R.string.picture_of_the_day_title,
                route = PictureOfTheDay,
                icon = Icons.Outlined.ThumbUp,
                selectedIcon = Icons.Filled.ThumbUp,
            ),
            TopLevelRoute(
                nameResId = R.string.asteroids_title,
                route = Asteroids,
                icon = Icons.Outlined.Warning,
                selectedIcon = Icons.Filled.Warning,
            ),
        )
    }

    @Serializable
    data object Weather : Destination

    @Serializable
    data object PictureOfTheDay : Destination

    @Serializable
    data object Asteroids : Destination {
        sealed interface Destination {
            @Serializable
            data object List : Destination

            @Serializable
            data class Info(
                @SerialName(ASTEROID_ID_KEY)
                val asteroidId: String
            ) : Destination {
                companion object {
                    const val ASTEROID_ID_KEY = "213123"
                }
            }
        }
    }
}