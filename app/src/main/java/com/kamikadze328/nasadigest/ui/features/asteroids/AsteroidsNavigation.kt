package com.kamikadze328.nasadigest.ui.features.asteroids

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.kamikadze328.nasadigest.ui.navigation.Destination
import com.kamikadze328.nasadigest.ui.features.asteroids.info.AsteroidInfoScreenUi
import com.kamikadze328.nasadigest.ui.features.asteroids.info.model.AsteroidInfoArgs
import com.kamikadze328.nasadigest.ui.features.asteroids.list.AsteroidsListScreenUi

fun NavGraphBuilder.asteroidsDestinations(
    navigateToAsteroidInfo: (AsteroidInfoArgs) -> Unit
) {
    navigation<Destination.Asteroids>(startDestination = Destination.Asteroids.Destination.List) {
        composable<Destination.Asteroids.Destination.List> {
            AsteroidsListScreenUi(navigateToAsteroidInfo = navigateToAsteroidInfo)
        }
        composable<Destination.Asteroids.Destination.Info> { AsteroidInfoScreenUi() }
    }
}

fun NavController.navigateToAsteroidInfo(args: AsteroidInfoArgs) {
    navigate(Destination.Asteroids.Destination.Info(asteroidId = args.asteroidId))
}