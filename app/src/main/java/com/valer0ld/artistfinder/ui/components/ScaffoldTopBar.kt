package com.valer0ld.artistfinder.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.valer0ld.artistfinder.ui.navigation.NavRoutes

@Composable
fun ScaffoldTopBar(navController: NavController, currentDestination: String?){
    if (currentDestination in listOf(
            NavRoutes.GameList.route,
            NavRoutes.Settings.route
        )){
        ReturnActionHeader(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
            navController.navigate("profile/main") {
                popUpTo("profile") {
                    inclusive = false
                }
                restoreState = true
            }
        }
    }
}