package com.valer0ld.artistfinder.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.valer0ld.artistfinder.ui.navigation.BottomNavigationBar
import com.valer0ld.artistfinder.ui.navigation.NavRoutes
import com.valer0ld.artistfinder.ui.screens.game.GameBottomBar
import com.valer0ld.artistfinder.ui.screens.win_game.WinGameBar
import kotlin.collections.contains

@Composable
fun ScaffoldBottomBar(navController: NavController, currentDestination: String?, currentStep: Int, currentReturns: Int, onGameBottomBarClick: ()->Unit){
    if (currentDestination in listOf(
            NavRoutes.Home.route,
            NavRoutes.ProfileMain.route,
            NavRoutes.GameList.route,
            NavRoutes.Settings.route,
            NavRoutes.Rating.route
        )){
        BottomNavigationBar(navController,
            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars))
    }
    if (currentDestination == NavRoutes.Game.route){
        GameBottomBar(navController,
            currentStep,
            currentReturns,
            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars), onGameBottomBarClick)
    }
    if (currentDestination == NavRoutes.WinGame.route){
        WinGameBar(
            navController,
            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
        )
    }
}