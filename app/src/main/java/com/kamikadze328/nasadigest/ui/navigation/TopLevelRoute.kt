package com.kamikadze328.nasadigest.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelRoute(
    @StringRes
    val nameResId: Int,
    val route: Destination,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
)