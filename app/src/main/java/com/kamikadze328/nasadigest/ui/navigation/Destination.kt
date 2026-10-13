package com.kamikadze328.nasadigest.ui.navigation

import com.kamikadze328.nasadigest.R
import com.kamikadze328.nasadigest.ui.icons.PlanetFilled
import com.kamikadze328.nasadigest.ui.icons.PlanetOutlined
import com.kamikadze328.nasadigest.ui.icons.SkullFilled
import com.kamikadze328.nasadigest.ui.icons.SkullOutlined
import com.kamikadze328.nasadigest.ui.icons.WbSunnyFilled
import com.kamikadze328.nasadigest.ui.icons.WbSunnyOutlined
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface Destination {
    companion object {
        val topLevelRoutes = listOf(
            TopLevelRoute(
                nameResId = R.string.weather_title,
                route = Weather,
                icon = WbSunnyOutlined,
                selectedIcon = WbSunnyFilled,
            ),
            TopLevelRoute(
                nameResId = R.string.picture_of_the_day_title,
                route = PictureOfTheDay,
                icon = PlanetOutlined,
                selectedIcon = PlanetFilled,
            ),
            TopLevelRoute(
                nameResId = R.string.asteroids_title,
                route = Asteroids,
                icon = SkullOutlined,
                selectedIcon = SkullFilled,
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
                @SerialName("asteroid_id")
                val asteroidId: String
            ) : Destination
        }
    }
}