package com.kamikadze328.nasadigest

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kamikadze328.nasadigest.ui.navigation.Destination
import com.kamikadze328.nasadigest.ui.navigation.TopLevelRoute
import com.kamikadze328.nasadigest.ui.features.asteroids.asteroidsDestinations
import com.kamikadze328.nasadigest.ui.features.asteroids.navigateToAsteroidInfo
import com.kamikadze328.nasadigest.ui.features.pictureoftheday.pictureOfTheDayDestination
import com.kamikadze328.nasadigest.ui.features.weather.weatherDestination


@Composable
fun NasaDigestScreenUi() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            NasaDigestBottomBar(
                currentDestination = currentDestination,
                onItemClick = { topLevelRoute ->
                    navController.onNavigationBarItemClick(topLevelRoute)
                },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destination.PictureOfTheDay,
            modifier = Modifier.padding(innerPadding),
        ) {
            weatherDestination()
            pictureOfTheDayDestination()
            asteroidsDestinations(
                navigateToAsteroidInfo = { args ->
                    navController.navigateToAsteroidInfo(args = args)
                }
            )
        }
    }
}

@Composable
private fun NasaDigestBottomBar(
    currentDestination: NavDestination?,
    onItemClick: (TopLevelRoute) -> Unit
) {
    NavigationBar {
        Destination.topLevelRoutes.forEach { topLevelRoute ->
            val isSelected = currentDestination.isSelected(topLevelRoute)
            NasaDigestNavigationBarItem(
                isSelected = isSelected,
                topLevelRoute = topLevelRoute,
                onClick = { onItemClick.invoke(topLevelRoute) },
            )
        }
    }
}

@Composable
private fun RowScope.NasaDigestNavigationBarItem(
    isSelected: Boolean,
    topLevelRoute: TopLevelRoute,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        icon = {
            Icon(
                imageVector = if (isSelected) topLevelRoute.selectedIcon else topLevelRoute.icon,
                contentDescription = stringResource(topLevelRoute.nameResId),
            )
        },
        label = {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(topLevelRoute.nameResId),
            )
        },
        selected = isSelected,
        onClick = onClick,
    )
}

private fun NavDestination?.isSelected(topLevelRoute: TopLevelRoute): Boolean {
    return this?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true
}

private fun NavController.onNavigationBarItemClick(topLevelRoute: TopLevelRoute) {
    val currentDestination = currentDestination
    if (currentDestination.isSelected(topLevelRoute)) {
        popBackStack()
    } else {
        navigate(topLevelRoute.route) {
            popUpTo(graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}




