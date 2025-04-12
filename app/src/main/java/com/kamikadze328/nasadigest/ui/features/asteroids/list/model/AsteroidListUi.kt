package com.kamikadze328.nasadigest.ui.features.asteroids.list.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap

@Immutable
data class AsteroidListUi(
    val data: ImmutableMap<String, ImmutableList<AsteroidInListUi>>,
)