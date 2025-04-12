package com.kamikadze328.nasadigest.ui.features.pictureoftheday

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kamikadze328.nasadigest.ui.navigation.Destination

fun NavGraphBuilder.pictureOfTheDayDestination() {
    composable<Destination.PictureOfTheDay> { PictureOfTheDayScreenUi() }
}