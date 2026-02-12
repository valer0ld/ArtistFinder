package com.valer0ld.artistfinder.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.valer0ld.artistfinder.ui.screens.home.Home
import com.valer0ld.artistfinder.ui.screens.settings.SettingsScreen
import com.valer0ld.artistfinder.ui.screens.game.GameScreen
import com.valer0ld.artistfinder.ui.screens.game_list.GamesList
import com.valer0ld.artistfinder.ui.screens.profile_main.ProfileMain
import com.valer0ld.artistfinder.ui.screens.win_game.WinGameScreen

sealed class NavRoutes(val route: String){
    object Home: NavRoutes("home")
    object ProfileMain: NavRoutes("profile/main")
    object GameList: NavRoutes("profile/games")
    object Settings: NavRoutes("profile/settings")
    //object Rating: NavRoutes("rating")
    object Game: NavRoutes("game")
    object WinGame: NavRoutes("win")
}

@Composable
fun NavGraph(navController: NavHostController, startDestination: String, innerPadding: PaddingValues, builder: NavGraphBuilder.()->Unit){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier
            .padding(
                start = innerPadding.calculateLeftPadding(LayoutDirection.Ltr),
                end = innerPadding.calculateRightPadding(LayoutDirection.Ltr),
                top = innerPadding.calculateTopPadding()
            ),

        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(durationMillis = 90)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it / 4 },
                animationSpec = tween(durationMillis = 90)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it / 4 },
                animationSpec = tween(durationMillis = 90)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(durationMillis = 90)
            )
        },
        builder = builder
    )
}

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    currentStep: Int,
    incrementStep: ()->Unit
){
    NavGraph(
        navController = navController,
        innerPadding = innerPadding,
        startDestination = NavRoutes.Home.route,
    ){
        composable(NavRoutes.Home.route) { Home(navController, innerPadding) }
        composable(NavRoutes.Game.route) { GameScreen(navController, innerPadding, currentStep, incrementStep) }
        composable(NavRoutes.WinGame.route) { WinGameScreen() }
    }
}

@Composable
fun ProfileNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onNavigationToGame: ()->Unit
){
    NavGraph(
        navController = navController,
        innerPadding = innerPadding,
        startDestination = NavRoutes.ProfileMain.route,
    ){
        composable(NavRoutes.ProfileMain.route) { ProfileMain(
            innerPadding,
            onGamesClick = { navController.navigate(NavRoutes.GameList.route) },
            onSettingsClick = { navController.navigate(NavRoutes.Settings.route) }
        ) }
        composable(NavRoutes.GameList.route) { GamesList(navController, onNavigationToGame) }
        composable(NavRoutes.Settings.route) { SettingsScreen(navController) }
    }
}