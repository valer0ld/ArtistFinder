package com.valer0ld.artistfinder.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.valer0ld.artistfinder.ui.navigation.NavRoutes

@Composable
fun ScaffoldTopBar(
    currentDestination: String?,
    onBackArrow: ()->Unit
){
    if (currentDestination in listOf(
            NavRoutes.GameList.route,
            NavRoutes.Settings.route
        )){
        ReturnActionHeader(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            onReturnClick = onBackArrow
        )
    }
}